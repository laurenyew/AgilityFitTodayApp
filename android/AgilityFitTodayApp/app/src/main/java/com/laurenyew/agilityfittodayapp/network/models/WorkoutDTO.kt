package com.laurenyew.agilityfittodayapp.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WorkoutSequenceDTO(
    val name: String,
    val description: String,
    val workoutItems: List<WorkoutItemDTO>,
    val workoutType: Int
)

data class WorkoutItemDTO(
    val quantity: Int,
    @Json(name = "itemBase") val itemBaseName: String
)