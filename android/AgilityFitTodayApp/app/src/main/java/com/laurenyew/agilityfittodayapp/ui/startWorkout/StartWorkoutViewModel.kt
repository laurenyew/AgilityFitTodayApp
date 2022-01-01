package com.laurenyew.agilityfittodayapp.ui.startWorkout

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class StartWorkoutViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : ViewModel() {
    val pagingWorkoutSequenceFlow: Flow<PagingData<WorkoutSequence>>

    init {
    }
}