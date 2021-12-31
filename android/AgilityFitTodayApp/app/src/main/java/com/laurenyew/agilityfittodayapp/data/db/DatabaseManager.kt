package com.laurenyew.agilityfittodayapp.data.db

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
    override suspend fun getFavoriteWorkoutSequences(workoutTypes: List<WorkoutType>): List<WorkoutSequence> =
        db.favoriteDao().getFavoriteWorkoutSequences(workoutTypes)

    override suspend fun getFavoriteWorkoutSequencesOrderedByType(): List<WorkoutSequence> =
        db.favoriteDao().getFavoriteWorkoutSequencesOrderedByType()

    override suspend fun getFavoriteWorkoutSequencesOrderedByName(): List<WorkoutSequence> =
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

    override suspend fun getFavoriteWorkoutItems(): List<WorkoutItem> =
        db.favoriteDao().getSavedWorkoutItems()
    //endregion

    //region Workout
    override suspend fun getWorkoutSequence(id: Long): WorkoutSequence? =
        db.workoutDao().getWorkoutSequence(id)

    override suspend fun getWorkoutSequences(workoutTypes: List<WorkoutType>): List<WorkoutSequence> =
        db.workoutDao().getWorkoutSequences(workoutTypes)

    override suspend fun getWorkoutSequencesOrderedByType(): List<WorkoutSequence> =
        db.workoutDao().getWorkoutSequencesOrderedByType()

    override suspend fun getWorkoutSequencesOrderedByName(): List<WorkoutSequence> =
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
    //endregion
}