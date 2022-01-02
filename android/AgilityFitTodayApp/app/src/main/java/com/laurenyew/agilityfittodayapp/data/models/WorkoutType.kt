package com.laurenyew.agilityfittodayapp.data.models

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader

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
    private val workoutTypeValues = WorkoutType.values()

    @FromJson
    fun fromJson(jsonReader: JsonReader, delegate: JsonAdapter<WorkoutType>): WorkoutType {
        val value = jsonReader.nextInt()
        return workoutTypeValues[value]
    }
}