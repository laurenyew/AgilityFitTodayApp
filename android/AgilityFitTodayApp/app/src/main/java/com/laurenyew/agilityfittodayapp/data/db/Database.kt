package com.laurenyew.agilityfittodayapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.laurenyew.agilityfittodayapp.data.models.User
import com.laurenyew.agilityfittodayapp.data.models.WorkoutItem
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.utils.WorkoutTypeConverters

@Database(
    entities = [
        User::class,
        WorkoutSequence::class,
        WorkoutItem::class
    ],
    version = 1
)
@TypeConverters(WorkoutTypeConverters::class)
abstract class AgilityFitTodayDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun favoriteDao(): FavoriteDao
}