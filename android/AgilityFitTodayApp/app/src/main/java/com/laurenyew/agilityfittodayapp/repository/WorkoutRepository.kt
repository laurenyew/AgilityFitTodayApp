package com.laurenyew.agilityfittodayapp.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.laurenyew.agilityfittodayapp.data.db.WorkoutDatabaseProvider
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.network.JsonFileReader
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class WorkoutRepository @Inject constructor(
    private val workoutDatabaseProvider: WorkoutDatabaseProvider,
    context: Context
) : WorkoutRepositoryAPI {
    private val workoutJsonFileReader = JsonFileReader()
    private val workoutRemoteMediator = WorkoutRemoteMediator(
        workoutDatabaseProvider,
        workoutJsonFileReader,
        context
    )

    override fun getWorkoutSequences(): Flow<PagingData<WorkoutSequence>> =
        Pager(
            config = PagingConfig(
                pageSize = WORKOUT_SEQUENCE_PAGE_SIZE,
                initialLoadSize = WORKOUT_SEQUENCE_PAGE_SIZE
            ),
            remoteMediator = workoutRemoteMediator
        ) {
            workoutDatabaseProvider.getWorkoutSequencesOrderedByType()
        }.flow

    override suspend fun getWorkoutSequence(id: Long): WorkoutSequence? =
        workoutDatabaseProvider.getWorkoutSequence(id)

    companion object {
        private const val WORKOUT_SEQUENCE_PAGE_SIZE = 4
    }
}

interface WorkoutRepositoryAPI {
    fun getWorkoutSequences(): Flow<PagingData<WorkoutSequence>>
    suspend fun getWorkoutSequence(id: Long): WorkoutSequence?
}