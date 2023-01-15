package com.laurenyew.agilityfittodayapp.utils

import android.os.CountDownTimer

/**
 * Timer for executing workout
 */
class CountDownTimer(
    millisInFuture: Long,
    countDownInterval: Long,
    val onIntervalTick: (() -> Unit),
    val onFinish: (() -> Unit)
) : CountDownTimer(
    millisInFuture,
    countDownInterval
) {
    override fun onTick(millisUntilFinished: Long) {
        onIntervalTick()
    }

    override fun onFinish() {
        onFinish
    }
}

class ExecuteWorkoutCountDownTimerWithPauseResume(millisInFuture: Long) {

}