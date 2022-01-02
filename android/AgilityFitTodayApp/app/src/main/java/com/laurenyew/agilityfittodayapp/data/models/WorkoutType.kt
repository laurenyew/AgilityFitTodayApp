package com.laurenyew.agilityfittodayapp.data.models

import com.squareup.moshi.FromJson

enum class WorkoutType {
    CARDIO,
    CORE,
    UPPER_BODY_STRENGTH,
    LOWER_BODY_STRENGTH,
    STRETCH,
    STRENGTH,
    REST;

    override fun toString(): String =
        when (this) {
            UPPER_BODY_STRENGTH -> "Upper-body Strength"
            LOWER_BODY_STRENGTH -> "Lower-body Strength"
            else -> name.toLowerCase().capitalize()
        }
}

class WorkoutTypeAdapter {
    @FromJson
    fun fromJson(value: String): WorkoutType = WorkoutType.valueOf(value)
}