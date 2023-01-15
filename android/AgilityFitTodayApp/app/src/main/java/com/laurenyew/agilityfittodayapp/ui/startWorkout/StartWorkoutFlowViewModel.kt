package com.laurenyew.agilityfittodayapp.ui.startWorkout

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.repository.WorkoutRepository
import com.laurenyew.agilityfittodayapp.ui.startWorkout.execute.WorkoutExecutionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * TODO: How to actually do the timer going through a workout?
 */
@HiltViewModel
class StartWorkoutFlowViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val navManager: StartWorkoutFlowNavManagerAPI
) : ViewModel(), StartWorkoutFlowNavManagerAPI by navManager {

    private var _selectedWorkout = MutableStateFlow<WorkoutSequence?>(null)
    val selectedWorkout: StateFlow<WorkoutSequence?> = _selectedWorkout

    // Get first sequences
    val workoutSequences: Flow<PagingData<WorkoutSequence>> =
        workoutRepository.getWorkoutSequences()
            .cachedIn(viewModelScope)

    private var _workoutState =
        MutableStateFlow<WorkoutExecutionState>(WorkoutExecutionState.NOT_STARTED)
    val workoutState: StateFlow<WorkoutExecutionState> = _workoutState

    private var _currentWorkoutItemIndex = MutableStateFlow(0)
    val currentWorkoutItemIndex: StateFlow<Int> = _currentWorkoutItemIndex

    private lateinit var countDownTimer: CountDownTimer

    // Workout Execution
    fun updateWorkoutState(newState: WorkoutExecutionState) {
        _workoutState.value = newState
        when (newState) {
            WorkoutExecutionState.NOT_STARTED -> restartWorkout()
            WorkoutExecutionState.IN_PROGRESS -> resumeWorkout()
            WorkoutExecutionState.STOPPED -> pauseWorkout()
            WorkoutExecutionState.COMPLETED -> finishWorkout()
            WorkoutExecutionState.CANCELLED -> finishWorkout(isCancelled = true)
        }
    }


    private fun restartWorkout() {
        // TODO: Do timer
//        countDownTimer = ExecuteWorkoutCountDownTimer(
//            selectedWorkout.value?.estimatedTime() ?: 0L
//        )
    }

    private fun pauseWorkout() {
        // TODO: Pause timer
    }

    private fun resumeWorkout() {
        //TODO: Restart timer from paused time
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
            _selectedWorkout.value = workoutRepository.getWorkoutSequence(sequenceId)
            navManager.updateNavRoute(StartWorkoutNavRoutes.ExecuteWorkout)
            restartWorkout()
        }
    }

    companion object {
        private const val COUNT_DOWN_INTERVAL_IN_MILLIS = 1000L
    }
}