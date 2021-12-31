package com.laurenyew.agilityfittodayapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.laurenyew.agilityfittodayapp.data.models.User
import com.laurenyew.agilityfittodayapp.data.models.WorkoutItem
import com.laurenyew.agilityfittodayapp.data.models.WorkoutItemBase
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@Database(
    entities = [
        User::class,
        WorkoutSequence::class,
        WorkoutItem::class
    ],
    version = 1
)
@TypeConverters(RoomEntityTypeConverters::class)
abstract class AgilityFitTodayDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun favoriteDao(): FavoriteDao
}

class RoomEntityTypeConverters {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val workoutItemListType =
        Types.newParameterizedType(List::class.java, WorkoutItem::class.java)
    private val workoutItemAdapter = moshi.adapter<List<WorkoutItem>>(workoutItemListType)


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
            else -> null
        }
}