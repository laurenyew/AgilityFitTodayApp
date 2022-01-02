package com.laurenyew.agilityfittodayapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeFormatter {
    private val hoursMinsSecsFormatter = SimpleDateFormat("hh:mm:ss")
    private val minsSecsFormatter = SimpleDateFormat("mm:ss")
    fun timeInMillisToDuration(timeInMillis: Long): String =
        if (timeInMillis > HOUR_TO_MILLIS) {
            hoursMinsSecsFormatter.format(Date(timeInMillis))
        } else {
            minsSecsFormatter.format(Date(timeInMillis))
        }
}