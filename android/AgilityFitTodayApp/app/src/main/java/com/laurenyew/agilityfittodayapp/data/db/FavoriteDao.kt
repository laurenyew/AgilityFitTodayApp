package com.laurenyew.agilityfittodayapp.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.laurenyew.agilityfittodayapp.data.models.WorkoutItem
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.data.models.WorkoutType

@Dao
interface FavoriteDao {
    @Query("SELECT * from workoutsequence WHERE isFavorite AND workoutType IN(:workoutTypes) ORDER by name ASC")
    fun getFavoriteWorkoutSequences(workoutTypes: List<WorkoutType>): PagingSource<Int, WorkoutSequence>

    @Query("SELECT * from workoutsequence WHERE isFavorite ORDER by workoutType ASC")
    fun getFavoriteWorkoutSequencesOrderedByType(): PagingSource<Int, WorkoutSequence>

    @Query("SELECT * from workoutsequence WHERE isFavorite ORDER by name ASC")
    fun getFavoriteWorkoutSequencesOrderedByName(): PagingSource<Int, WorkoutSequence>

    @Query("SELECT * from workoutitem")
    fun getSavedWorkoutItems(): PagingSource<Int, WorkoutItem>

    @Query("SELECT * from workoutitem WHERE id = :id")
    suspend fun getSavedWorkoutItem(id: Long): WorkoutItem?

    @Query("DELETE FROM workoutitem WHERE id = :id")
    suspend fun deleteSavedWorkoutItem(id: Long)

    @Insert
    suspend fun insertSavedWorkoutItem(workoutItem: WorkoutItem)
}

interface FavoriteDatabaseProvider {
    fun getFavoriteWorkoutSequences(workoutTypes: List<WorkoutType>): PagingSource<Int, WorkoutSequence>
    fun getFavoriteWorkoutSequencesOrderedByType(): PagingSource<Int, WorkoutSequence>
    fun getFavoriteWorkoutSequencesOrderedByName(): PagingSource<Int, WorkoutSequence>
    suspend fun favoriteWorkoutSequence(id: Long)
    suspend fun unfavoriteWorkoutSequence(id: Long)

    suspend fun favoriteWorkoutItem(workoutItem: WorkoutItem)
    suspend fun unfavoriteWorkoutItem(id: Long)
    fun getFavoriteWorkoutItems(): PagingSource<Int, WorkoutItem>
}