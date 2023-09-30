package com.laurenyew.agilityfittodayapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeFormatter {
    private val hoursMinsSecsFormatter = SimpleDateFormat("hh:mm:ss", Locale.US)
    private val minsSecsFormatter = SimpleDateFormat("mm:ss", Locale.US)
    fun timeInMillisToDuration(timeInMillis: Long): String =
        if (timeInMillis > HOUR_TO_MILLIS) {
            hoursMinsSecsFormatter.format(Date(timeInMillis))
        } else {
            minsSecsFormatter.format(Date(timeInMillis))
        }
}
