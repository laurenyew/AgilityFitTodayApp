package com.laurenyew.agilityfittodayapp.network.models

import com.laurenyew.agilityfittodayapp.data.models.WorkoutType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WorkoutSequenceDTO(
    val name: String,
    val description: String,
    val workoutItems: List<WorkoutItemDTO>,
    val workoutType: WorkoutType
)

data class WorkoutItemDTO(
    val quantity: Int,
    @Json(name = "itemBase") val itemBaseName: String
)