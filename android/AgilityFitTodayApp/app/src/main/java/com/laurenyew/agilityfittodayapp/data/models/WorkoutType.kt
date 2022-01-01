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
    REST
}

class WorkoutTypeAdapter {
    private val workoutTypeValues = WorkoutType.values()

    @FromJson
    fun fromJson(jsonReader: JsonReader, delegate: JsonAdapter<WorkoutType>): WorkoutType {
        val value = jsonReader.nextInt()
        return workoutTypeValues[value]
    }
}