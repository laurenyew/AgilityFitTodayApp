package com.laurenyew.agilityfittodayapp.data.db

import androidx.paging.PagingSource
import com.laurenyew.agilityfittodayapp.data.models.User
import com.laurenyew.agilityfittodayapp.data.models.WorkoutItem
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.data.models.WorkoutType
import javax.inject.Inject

class DatabaseManager @Inject constructor(
    private val db: AgilityFitTodayDatabase
) : UserDatabaseProvider, WorkoutDatabaseProvider, FavoriteDatabaseProvider {
    //region User
    override suspend fun getUser(id: Long): User? =
        db.userDao().getUser(id)

    override suspend fun deleteUser(id: Long) {
        db.userDao().deleteUser(id)
    }

    override suspend fun createUser(user: User) {
        db.userDao().insert(user)
    }
    //endregion

    //region Favorites
    override fun getFavoriteWorkoutSequences(workoutTypes: List<WorkoutType>): PagingSource<Int, WorkoutSequence> =
        db.favoriteDao().getFavoriteWorkoutSequences(workoutTypes)

    override fun getFavoriteWorkoutSequencesOrderedByType(): PagingSource<Int, WorkoutSequence> =
        db.favoriteDao().getFavoriteWorkoutSequencesOrderedByType()

    override fun getFavoriteWorkoutSequencesOrderedByName(): PagingSource<Int, WorkoutSequence> =
        db.favoriteDao().getFavoriteWorkoutSequencesOrderedByName()

    override suspend fun favoriteWorkoutSequence(id: Long) {
        val workoutSeq = db.workoutDao().getWorkoutSequence(id)
        workoutSeq?.copy(isFavorite = true)?.let {
            db.workoutDao().insert(it)
        }
    }

    override suspend fun unfavoriteWorkoutSequence(id: Long) {
        val workoutSeq = db.workoutDao().getWorkoutSequence(id)
        workoutSeq?.copy(isFavorite = false)?.let {
            db.workoutDao().insert(it)
        }
    }

    override suspend fun favoriteWorkoutItem(workoutItem: WorkoutItem) {
        db.favoriteDao().insertSavedWorkoutItem(workoutItem)
    }

    override suspend fun unfavoriteWorkoutItem(id: Long) {
        db.favoriteDao().deleteSavedWorkoutItem(id)
    }

    override fun getFavoriteWorkoutItems(): PagingSource<Int, WorkoutItem> =
        db.favoriteDao().getSavedWorkoutItems()
    //endregion

    //region Workout
    override suspend fun hasWorkoutSequences(): Boolean =
        db.workoutDao().getWorkoutSequenceCount() > 0

    override suspend fun getWorkoutSequence(id: Long): WorkoutSequence? =
        db.workoutDao().getWorkoutSequence(id)

    override fun getWorkoutSequences(workoutTypes: List<WorkoutType>): PagingSource<Int, WorkoutSequence> =
        db.workoutDao().getWorkoutSequences(workoutTypes)

    override fun getWorkoutSequencesOrderedByType(): PagingSource<Int, WorkoutSequence> =
        db.workoutDao().getWorkoutSequencesOrderedByType()

    override fun getWorkoutSequencesOrderedByName(): PagingSource<Int, WorkoutSequence> =
        db.workoutDao().getWorkoutSequencesOrderedByName()

    override suspend fun deleteWorkoutSequence(id: Long) {
        db.workoutDao().deleteWorkoutSequence(id)
    }

    override suspend fun updateWorkoutSequence(workoutSequence: WorkoutSequence) {
        db.workoutDao().insert(workoutSequence)
    }

    override suspend fun createWorkoutSequence(workoutSequence: WorkoutSequence) {
        db.workoutDao().insert(workoutSequence)
    }

    override suspend fun createWorkoutSequences(workoutSequences: List<WorkoutSequence>) {
        db.workoutDao().insertAll(workoutSequences)
    }
    //endregion
}