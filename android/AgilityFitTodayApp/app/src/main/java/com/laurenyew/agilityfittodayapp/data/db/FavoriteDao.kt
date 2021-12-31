package com.laurenyew.agilityfittodayapp.data.db

import androidx.room.Dao
import androidx.room.Query
import com.laurenyew.agilityfittodayapp.data.models.WorkoutItem
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.data.models.WorkoutType

@Dao
interface FavoriteDao {
    @Query("SELECT * from workoutsequence WHERE isFavorite AND workoutType IN(:workoutTypes) ORDER by name DESC")
    suspend fun getFavoriteWorkoutSequences(workoutTypes: List<WorkoutType>): List<WorkoutSequence>

    @Query("SELECT * from workoutsequence WHERE isFavorite ORDER by workoutType DESC")
    suspend fun getFavoriteWorkoutSequencesOrderedByType(): List<WorkoutSequence>

    @Query("SELECT * from workoutsequence WHERE isFavorite ORDER by name DESC")
    suspend fun getFavoriteWorkoutSequencesOrderedByName(): List<WorkoutSequence>

    @Query("SELECT * from workoutitem")
    suspend fun getFavoriteWorkoutItems(): List<WorkoutItem>
}

interface FavoriteDatabaseProvider {
    suspend fun getFavoriteWorkoutSequences(workoutTypes: List<WorkoutType>): List<WorkoutSequence>
    suspend fun getFavoriteWorkoutSequencesOrderedByType(): List<WorkoutSequence>
    suspend fun getFavoriteWorkoutSequencesOrderedByName(): List<WorkoutSequence>
    suspend fun favoriteWorkoutSequence(id: Long)
    suspend fun unfavoriteWorkoutSequence(id: Long)

    suspend fun favoriteWorkoutItem(id: Long)
    suspend fun unfavoriteWorkoutItem(id: Long)
    suspend fun getFavoriteWorkoutItems(): List<WorkoutItem>
}