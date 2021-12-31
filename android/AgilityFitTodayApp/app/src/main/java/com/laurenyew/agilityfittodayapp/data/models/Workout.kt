package com.laurenyew.agilityfittodayapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutSequence(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val workoutItems: List<WorkoutItem>,
    val workoutType: WorkoutType,
    val isFavorite: Boolean
) {
    fun estimatedTime(): Long =
        workoutItems.sumOf {
            it.estimatedTime()
        }
}

@Entity
data class WorkoutItem(
    @PrimaryKey val id: Long,
    val quantity: Int,
    val itemBase: WorkoutItemBase
) {
    fun estimatedTime(): Long = itemBase.baseEstimatedTime * quantity
}