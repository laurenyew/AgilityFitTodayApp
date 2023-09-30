package com.laurenyew.agilityfittodayapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.laurenyew.agilityfittodayapp.utils.DateTimeFormatter
import com.laurenyew.agilityfittodayapp.utils.MIN_TO_MILLIS

@Entity
data class WorkoutSequence(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val workoutItems: List<WorkoutItem>,
    val workoutType: WorkoutType,
    val isFavorite: Boolean
) {
    /**
     * Estimated time of workout sequence in millis
     */
    fun estimatedTime(): Long =
        workoutItems.sumOf {
            it.estimatedTime()
        }

    fun workoutItemSeqTiming(): List<WorkoutItemSeqTiming> {
        var seqTime = 0L
        return workoutItems.map {
            seqTime += it.estimatedTime()
            WorkoutItemSeqTiming(
                it,
                seqTime
            )
        }
    }
}

/**
 * Helper data class
 * @param item: WorkoutItem
 * @param sequenceTiming: total millis that sequence reaches to get to workout
 */
data class WorkoutItemSeqTiming(
    val item: WorkoutItem,
    val sequenceTiming: Long
)

fun WorkoutSequence.estimatedTimeFormattedString(): String =
    DateTimeFormatter.timeInMillisToDuration(this.estimatedTime())

@Entity
data class WorkoutItem(
    @PrimaryKey val id: Long,
    val quantity: Int,
    val itemBase: WorkoutItemBase,
    val isFavorite: Boolean = false
) {
    fun estimatedTime(): Long = itemBase.baseEstimatedTime * quantity * MIN_TO_MILLIS
}

fun WorkoutItem.estimatedTimeFormattedString(): String =
    DateTimeFormatter.timeInMillisToDuration(this.estimatedTime())
