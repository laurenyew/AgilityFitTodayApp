package com.laurenyew.agilityfittodayapp.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.laurenyew.agilityfittodayapp.data.db.WorkoutDatabaseProvider
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.network.JsonFileReader

@OptIn(ExperimentalPagingApi::class)
class WorkoutRemoteMediator(
    private val db: WorkoutDatabaseProvider,
    private val network: JsonFileReader,
    private val context: Context
) : RemoteMediator<Int, WorkoutSequence>() {

    // Make sure to refresh on initial load
    override suspend fun initialize(): InitializeAction =
        InitializeAction.LAUNCH_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, WorkoutSequence>
    ): MediatorResult {
        if (loadType == LoadType.REFRESH) {
            val items = network.getBaseWorkoutSequences(context)
            db.createWorkoutSequences(items)
        }
        return MediatorResult.Success(endOfPaginationReached = true)
    }
}