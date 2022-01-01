package com.laurenyew.agilityfittodayapp.utils

import androidx.room.TypeConverter
import com.laurenyew.agilityfittodayapp.data.models.WorkoutItem
import com.laurenyew.agilityfittodayapp.data.models.WorkoutItemBase
import com.laurenyew.agilityfittodayapp.data.models.WorkoutType
import com.laurenyew.agilityfittodayapp.network.models.WorkoutSequenceDTO
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object WorkoutTypeConverters {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val workoutItemListType =
        Types.newParameterizedType(List::class.java, WorkoutItem::class.java)
    private val workoutItemAdapter = moshi.adapter<List<WorkoutItem>>(workoutItemListType)

    private val workoutSequenceDTOListType =
        Types.newParameterizedType(List::class.java, WorkoutSequenceDTO::class.java)
    val workoutSequenceDTOListAdapter: JsonAdapter<List<WorkoutSequenceDTO>> =
        moshi.adapter<List<WorkoutSequenceDTO>>(workoutSequenceDTOListType)

    private val workoutTypeValues = WorkoutType.values()

    @TypeConverter
    fun workoutItemListToJson(value: List<WorkoutItem>?): String =
        workoutItemAdapter.toJson(value)


    @TypeConverter
    fun jsonToWorkoutItemList(value: String): List<WorkoutItem>? =
        workoutItemAdapter.fromJson(value)

    @TypeConverter
    fun workoutItemBaseToString(value: WorkoutItemBase): String = value.name

    @TypeConverter
    fun stringToWorkoutItemBase(value: String): WorkoutItemBase? =
        when (value) {
            WorkoutItemBase.Rest.name -> WorkoutItemBase.Rest
            WorkoutItemBase.Crunches.name -> WorkoutItemBase.Crunches
            WorkoutItemBase.PushUps.name -> WorkoutItemBase.PushUps
            WorkoutItemBase.Squats.name -> WorkoutItemBase.Squats
            WorkoutItemBase.Treadmill.name -> WorkoutItemBase.Treadmill
            WorkoutItemBase.Stretch.name -> WorkoutItemBase.Stretch
            else -> null
        }

    @TypeConverter
    fun intToWorkoutType(value: Int): WorkoutType = workoutTypeValues[value]
}