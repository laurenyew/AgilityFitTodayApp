package com.laurenyew.agilityfittodayapp.data.models

import com.laurenyew.agilityfittodayapp.utils.ONE_MINUTE_IN_SECONDS
import com.laurenyew.agilityfittodayapp.utils.WorkoutTypeConverters
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

sealed class WorkoutItemBase(
    val name: String,
    val description: String,
    val baseEstimatedTimeInSecs: Long, // in seconds
    val isMeasuredInReps: Boolean,
    val workoutType: WorkoutType
) {
    object Rest : WorkoutItemBase(
        name = "Rest",
        description = "Rest, Breathe, & Hydrate",
        baseEstimatedTimeInSecs = ONE_MINUTE_IN_SECONDS,
        isMeasuredInReps = false,
        workoutType = WorkoutType.REST
    )

    object Crunches : WorkoutItemBase(
        name = "Crunches",
        description = "On your back, hands behind head, half sit-up",
        baseEstimatedTimeInSecs = 10,
        isMeasuredInReps = true,
        workoutType = WorkoutType.CORE
    )

    object PushUps : WorkoutItemBase(
        name = "Push-ups",
        description = "In a plank position with your hands below your shoulders" +
                ", bend and straighten your elbows to 90 degrees",
        baseEstimatedTimeInSecs = 5,
        isMeasuredInReps = true,
        workoutType = WorkoutType.UPPER_BODY_STRENGTH
    )

    object Squats : WorkoutItemBase(
        name = "Squats",
        description = "Knees over ankles, bend your knees with straight back (w/ or w/o weights)",
        baseEstimatedTimeInSecs = 10,
        isMeasuredInReps = true,
        workoutType = WorkoutType.LOWER_BODY_STRENGTH
    )

    object Treadmill : WorkoutItemBase(
        name = "Treadmill",
        description = "Walk / Run in intervals on the treadmill",
        baseEstimatedTimeInSecs = 5 * ONE_MINUTE_IN_SECONDS,
        isMeasuredInReps = false,
        workoutType = WorkoutType.CARDIO
    )

    object Stretch : WorkoutItemBase(
        name = "Stretch",
        description = "Stretch / Cooldown",
        baseEstimatedTimeInSecs = 5 * ONE_MINUTE_IN_SECONDS,
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
