package com.laurenyew.agilityfittodayapp.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Create a timer that just counts time passed since start
 * This timer can still be paused or resumed
 */
class TimePassedTimer(
    private val countDownInterval: Long,
    private val onIntervalTick: (timerData: TimerData) -> Unit,
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
    private var timer: Flow<TimerData>? = null
    private var pausedTimerData: TimerData? = null
    private val onPauseResumeIntervalTick: ((timerData: TimerData) -> Unit) =
        { timerData ->
            pausedTimerData = timerData
            onIntervalTick(timerData)
        }

    init {
        restart()
    }

    fun restart() {
        cancel()
        scope.launch {
            timer = timerFlow(
                pausedTimerData = pausedTimerData?.copy(timePassedInWorkout = 0L),
                countDownInterval = countDownInterval,
                onIntervalTick = onPauseResumeIntervalTick
            )
            timer?.collect()
        }
    }

    fun pause() {
        scope.cancel("Pausing Timer")
        _scope = null
    }

    fun resume() {
        scope.launch {
            timer = timerFlow(
                pausedTimerData = pausedTimerData,
                countDownInterval = countDownInterval,
                onIntervalTick = onPauseResumeIntervalTick,
            )
            timer?.collect()
        }
    }

    fun cancel() {
        _scope?.cancel("Cancelling Timer")
        _scope = null
        timer = null
        pausedTimerData = null
    }

    /**
     * Create a basic flow that can simulate a timer
     */
    private fun timerFlow(
        countDownInterval: Long,
        onIntervalTick: (timerData: TimerData) -> Unit,
        pausedTimerData: TimerData? = null
    ): Flow<TimerData> =
        flow {
            var timePassedSinceFirstStart = pausedTimerData?.totalTimePassedSinceFirstStart ?: 0L
            var timePassedInWorkout = pausedTimerData?.timePassedInWorkout ?: 0L

            while (true) {
                delay(countDownInterval)
                timePassedSinceFirstStart += countDownInterval
                timePassedInWorkout += countDownInterval
                emit(
                    TimerData(
                        timePassedInWorkout = timePassedInWorkout,
                        totalTimePassedSinceFirstStart = timePassedSinceFirstStart
                    )
                )
            }
        }
            .onEach { timerData ->
                onIntervalTick(timerData)
            }
}

/**
 * @param timePassedInWorkout : Amount of time in the workout that has passed
 * (used to figure out the workout index we are on)
 * @param totalTimePassedSinceFirstStart : Total time passed (ignores restarts)
 * since first starting on workout
 */
data class TimerData(
    val timePassedInWorkout: Long,
    val totalTimePassedSinceFirstStart: Long
)
