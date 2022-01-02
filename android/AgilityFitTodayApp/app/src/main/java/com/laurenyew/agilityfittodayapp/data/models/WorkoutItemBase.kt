package com.laurenyew.agilityfittodayapp.data.models

import com.laurenyew.agilityfittodayapp.utils.WorkoutTypeConverters
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

sealed class WorkoutItemBase(
    val name: String,
    val description: String,
    val baseEstimatedTime: Long, // in minutes
    val isMeasuredInReps: Boolean,
    val workoutType: WorkoutType
) {
    object Rest : WorkoutItemBase(
        name = "Rest",
        description = "Rest, Breathe, & Hydrate",
        baseEstimatedTime = 1,
        isMeasuredInReps = false,
        workoutType = WorkoutType.REST
    )

    object Crunches : WorkoutItemBase(
        name = "Crunches",
        description = "On your back, hands behind head, half sit-up",
        baseEstimatedTime = 1,
        isMeasuredInReps = true,
        workoutType = WorkoutType.CORE
    )

    object PushUps : WorkoutItemBase(
        name = "Push-ups",
        description = "In a plank position with your hands below your shoulders, bend and straighten your elbows to 90 degrees",
        baseEstimatedTime = 1,
        isMeasuredInReps = true,
        workoutType = WorkoutType.UPPER_BODY_STRENGTH
    )

    object Squats : WorkoutItemBase(
        name = "Squats",
        description = "Knees over ankles, bend your knees with straight back (w/ or w/o weights)",
        baseEstimatedTime = 1,
        isMeasuredInReps = true,
        workoutType = WorkoutType.LOWER_BODY_STRENGTH
    )

    object Treadmill : WorkoutItemBase(
        name = "Treadmill",
        description = "Walk / Run in intervals on the treadmill",
        baseEstimatedTime = 5,
        isMeasuredInReps = false,
        workoutType = WorkoutType.CARDIO
    )

    object Stretch : WorkoutItemBase(
        name = "Stretch",
        description = "Stretch / Cooldown",
        baseEstimatedTime = 5,
        isMeasuredInReps = false,
        workoutType = WorkoutType.STRETCH
    )
}

class WorkoutItemBaseAdapter {
    @FromJson
    fun fromJson(jsonString: String): WorkoutItemBase? =
        WorkoutTypeConverters.stringToWorkoutItemBase(jsonString)

    @ToJson
    fun toJson(workoutItemBase: WorkoutItemBase): String = workoutItemBase.name
}