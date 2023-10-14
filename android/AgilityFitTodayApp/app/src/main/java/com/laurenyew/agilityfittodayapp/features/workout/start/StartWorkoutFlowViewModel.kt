package com.laurenyew.agilityfittodayapp.features.workout.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.features.workout.ExecuteWorkoutManagerAPI
import com.laurenyew.agilityfittodayapp.features.workout.ExecuteWorkoutManagerStateAPI
import com.laurenyew.agilityfittodayapp.features.workout.execute.WorkoutExecutionState
import com.laurenyew.agilityfittodayapp.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * TODO: How to actually do the timer going through a workout?
 */
@HiltViewModel
class StartWorkoutFlowViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val navManager: StartWorkoutFlowNavManagerAPI,
    private val executeWorkoutManager: ExecuteWorkoutManagerAPI
) : ViewModel(),
    StartWorkoutFlowNavManagerAPI by navManager,
    ExecuteWorkoutManagerStateAPI by executeWorkoutManager {

    // Get first sequences
    val workoutSequences: Flow<PagingData<WorkoutSequence>> =
        workoutRepository.getWorkoutSequences()
            .cachedIn(viewModelScope)

    init {
        executeWorkoutManager.onResetWorkoutSelection()
    }

    // Workout Execution

    fun updateWorkoutState(newState: WorkoutExecutionState) {
        when (newState) {
            WorkoutExecutionState.COMPLETED -> finishWorkout()
            WorkoutExecutionState.CANCELLED -> finishWorkout(isCancelled = true)
            else -> executeWorkoutManager.updateWorkoutState(newState = newState)
        }
    }

    fun setUsingWorkoutTimer(isUsing: Boolean) {
        executeWorkoutManager.setUsingWorkoutTimer(isUsing)
    }

    private fun finishWorkout(isCancelled: Boolean = false) {
        navManager.updateNavRoute(StartWorkoutNavRoutes.CompletedWorkout)
    }

    // Flow Navigation

    /**
     * Select a Workout to Preview
     */
    fun onSelectWorkoutSequence(sequenceId: Long) {
        viewModelScope.launch {
            executeWorkoutManager.setSelectedWorkout(
                workoutRepository.getWorkoutSequence(sequenceId)
            )
            navManager.updateNavRoute(StartWorkoutNavRoutes.ExecuteWorkout)
            updateWorkoutState(WorkoutExecutionState.NOT_STARTED)
        }
    }

    fun onResetWorkoutSelection() {
        viewModelScope.launch {
            executeWorkoutManager.setSelectedWorkout(null)
            executeWorkoutManager.onResetWorkoutSelection()
        }
    }
}
