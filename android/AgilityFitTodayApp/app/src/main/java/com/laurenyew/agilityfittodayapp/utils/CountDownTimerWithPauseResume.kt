package com.laurenyew.agilityfittodayapp.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Create a timer with pause / resume / restart capabilities
 * that will alert us on a given interval
 * and let us know when it is finished
 *
 * @param workoutTime [Long] value representing total time this Timer will run
 * @param countDownInterval [Long] value in millis on each interval delay
 * @param onIntervalTick callback for when hits interval
 * @param onCountDownComplete callback when the countdown completes
 * @param testScope used for testing, provide a scope
 */
class CountDownTimerWithPauseResume(
    private val workoutTime: Long,
    private val countDownInterval: Long,
    private val onIntervalTick: (timerData: TimerData) -> Unit,
    private val onCountDownComplete: (() -> Unit),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private var _scope: CoroutineScope? = null
    private val scope: CoroutineScope
        get() {
            if (_scope == null) {
                _scope = CoroutineScope(ioDispatcher)
            }
            return _scope!!
        }
    private var countDownTimer: Flow<TimerData>? = null
    private var pausedTimerData: TimerData? = null
    private val onPauseResumeIntervalTick: ((timerData: TimerData) -> Unit) =
        { timerData ->
            pausedTimerData = timerData
            onIntervalTick(timerData)
        }

    private val onPauseResumeCountDownComplete: (() -> Unit) = {
        pausedTimerData = null
        onCountDownComplete()
    }

    init {
        restart()
    }

    fun restart() {
        cancel()
        scope.launch {
            countDownTimer = countDownTimerFlow(
                workoutTime = workoutTime,
                pausedTimerData = pausedTimerData?.copy(timePassedInWorkout = 0L),
                countDownInterval = countDownInterval,
                onIntervalTick = onPauseResumeIntervalTick,
                onCountDownComplete = onPauseResumeCountDownComplete
            )
            countDownTimer?.collect()
        }
    }

    fun pause() {
        scope.cancel("Pausing Timer")
        _scope = null
    }

    fun resume() {
        scope.launch {
            countDownTimer = countDownTimerFlow(
                workoutTime = pausedTimerData?.timeUntilFinished ?: workoutTime,
                pausedTimerData = pausedTimerData,
                countDownInterval = countDownInterval,
                onIntervalTick = onPauseResumeIntervalTick,
                onCountDownComplete = onPauseResumeCountDownComplete
            )
            countDownTimer?.collect()
        }
    }

    fun cancel() {
        _scope?.let {
            it.cancel("Cancelling Timer")
        }
        _scope = null
        countDownTimer = null
        pausedTimerData = null
    }

    /**
     * Create a basic flow that can simulate a timer
     */
    private fun countDownTimerFlow(
        workoutTime: Long,
        countDownInterval: Long,
        onIntervalTick: (timerData: TimerData) -> Unit,
        onCountDownComplete: () -> Unit,
        pausedTimerData: TimerData? = null
    ): Flow<TimerData> =
        flow {
            var totalTime = workoutTime
            var timePassedSinceFirstStart = pausedTimerData?.totalTimePassedSinceFirstStart ?: 0L
            var timePassedInWorkout = pausedTimerData?.timePassedInWorkout ?: 0L

            while (totalTime > 0) {
                delay(countDownInterval)
                val timeUntilFinished = totalTime - countDownInterval
                totalTime = timeUntilFinished
                timePassedSinceFirstStart += countDownInterval
                timePassedInWorkout += countDownInterval
                emit(
                    TimerData(
                        timeUntilFinished = timeUntilFinished,
                        timePassedInWorkout = timePassedInWorkout,
                        totalTimePassedSinceFirstStart = timePassedSinceFirstStart
                    )
                )
            }
        }
            .onEach { timerData ->
                onIntervalTick(timerData)
            }
            .onCompletion { cancelledException ->
                if (cancelledException == null) {
                    onCountDownComplete()
                }
            }
}

/**
 * @param timeUntilFinished : Time in millis until we are finished with the workout
 * @param timePassedInWorkout : Amount of time in the workout that has passed
 * (used to figure out the workout index we are on)
 * @param totalTimePassedSinceFirstStart : Total time passed (ignores restarts)
 * since first starting on workout
 */
data class TimerData(
    val timeUntilFinished: Long,
    val timePassedInWorkout: Long,
    val totalTimePassedSinceFirstStart: Long
)
