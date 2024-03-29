package com.laurenyew.agilityfittodayapp.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.data.models.WorkoutType

@Dao
interface WorkoutDao {
    @Query("SELECT COUNT(*) from workoutsequence")
    suspend fun getWorkoutSequenceCount(): Int

    @Query("SELECT * from workoutsequence WHERE id = :id")
    suspend fun getWorkoutSequence(id: Long): WorkoutSequence?

    @Query("SELECT * from workoutsequence WHERE workoutType IN(:workoutTypes) ORDER by name ASC")
    fun getWorkoutSequences(workoutTypes: List<WorkoutType>): PagingSource<Int, WorkoutSequence>

    @Query("SELECT * from workoutsequence ORDER by workoutType ASC")
    fun getWorkoutSequencesOrderedByType(): PagingSource<Int, WorkoutSequence>

    @Query("SELECT * from workoutsequence ORDER by name ASC")
    fun getWorkoutSequencesOrderedByName(): PagingSource<Int, WorkoutSequence>

    @Query("DELETE FROM workoutsequence WHERE id = :id")
    suspend fun deleteWorkoutSequence(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workoutSequence: WorkoutSequence)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(workoutSequences: List<WorkoutSequence>)
}

interface WorkoutDatabaseProvider {
    suspend fun hasWorkoutSequences(): Boolean
    suspend fun getWorkoutSequence(id: Long): WorkoutSequence?
    fun getWorkoutSequences(workoutTypes: List<WorkoutType>): PagingSource<Int, WorkoutSequence>
    fun getWorkoutSequencesOrderedByType(): PagingSource<Int, WorkoutSequence>
    fun getWorkoutSequencesOrderedByName(): PagingSource<Int, WorkoutSequence>
    suspend fun deleteWorkoutSequence(id: Long)
    suspend fun updateWorkoutSequence(workoutSequence: WorkoutSequence)
    suspend fun createWorkoutSequence(workoutSequence: WorkoutSequence)
    suspend fun createWorkoutSequences(workoutSequences: List<WorkoutSequence>)
}
