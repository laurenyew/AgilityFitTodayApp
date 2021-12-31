package com.laurenyew.agilityfittodayapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.data.models.WorkoutType

@Dao
interface WorkoutDao {
    @Query("SELECT * from workoutsequence WHERE id = :id")
    suspend fun getWorkoutSequence(id: Long): WorkoutSequence?

    @Query("SELECT * from workoutsequence WHERE workoutType IN(:workoutTypes) ORDER by name DESC")
    suspend fun getWorkoutSequences(workoutTypes: List<WorkoutType>): List<WorkoutSequence>

    @Query("SELECT * from workoutsequence ORDER by workoutType DESC")
    suspend fun getWorkoutSequencesOrderedByType(): List<WorkoutSequence>

    @Query("SELECT * from workoutsequence ORDER by name DESC")
    suspend fun getWorkoutSequencesOrderedByName(): List<WorkoutSequence>

    @Query("DELETE FROM workoutsequence WHERE id = :id")
    suspend fun deleteWorkoutSequence(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workoutSequence: WorkoutSequence)
}

interface WorkoutDatabaseProvider {
    suspend fun getWorkoutSequence(id: Long): WorkoutSequence?
    suspend fun getWorkoutSequences(workoutTypes: List<WorkoutType>): List<WorkoutSequence>
    suspend fun getWorkoutSequencesOrderedByType(): List<WorkoutSequence>
    suspend fun getWorkoutSequencesOrderedByName(): List<WorkoutSequence>
    suspend fun deleteWorkoutSequence(id: Long)
    suspend fun updateWorkoutSequence(workoutSequence: WorkoutSequence)
    suspend fun createWorkoutSequence(workoutSequence: WorkoutSequence)
}