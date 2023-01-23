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
 * @param millisInFuture [Long] value representing total time this Timer will run
 * @param countDownInterval [Long] value in millis on each interval delay
 * @param onIntervalTick callback for when hits interval
 * @param onCountDownComplete callback when the countdown completes
 * @param testScope used for testing, provide a scope
 */
class CountDownTimerWithPauseResume(
    private val millisInFuture: Long,
    private val countDownInterval: Long,
    private val onIntervalTick: ((millisUntilFinished: Long) -> Unit),
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
    private var countDownTimer: Flow<Long>? = null
    private var pausedMillisUntilFinished: Long? = null
    private val onPauseResumeIntervalTick: ((millisUntilFinished: Long) -> Unit) =
        { millisUntilFinished ->
            pausedMillisUntilFinished = millisUntilFinished
            onIntervalTick(millisUntilFinished)
        }

    private val onPauseResumeCountDownComplete: (() -> Unit) = {
        pausedMillisUntilFinished = null
        onCountDownComplete()
    }

    init {
        restart()
    }

    fun restart() {
        scope.launch {
            countDownTimer = countDownTimerFlow(
                millisInFuture = millisInFuture,
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
                millisInFuture = pausedMillisUntilFinished ?: millisInFuture,
                countDownInterval = countDownInterval,
                onIntervalTick = onPauseResumeIntervalTick,
                onCountDownComplete = onPauseResumeCountDownComplete
            )
            countDownTimer?.collect()
        }
    }

    fun cancel() {
        scope.cancel("Cancelling Timer")
        countDownTimer = null
        pausedMillisUntilFinished = null
    }

    /**
     * Create a basic flow that can simulate a timer
     */
    private fun countDownTimerFlow(
        millisInFuture: Long,
        countDownInterval: Long,
        onIntervalTick: (millisUntilFinished: Long) -> Unit,
        onCountDownComplete: () -> Unit
    ): Flow<Long> =
        flow {
            var totalTime = millisInFuture
            while (totalTime > 0) {
                delay(countDownInterval)
                val timeUntilFinished = totalTime - countDownInterval
                totalTime = timeUntilFinished
                emit(timeUntilFinished)
            }
        }
            .onEach { timeUntilFinished ->
                onIntervalTick(timeUntilFinished)
            }
            .onCompletion { cancelledException ->
                if (cancelledException == null) {
                    onCountDownComplete()
                }
            }
}