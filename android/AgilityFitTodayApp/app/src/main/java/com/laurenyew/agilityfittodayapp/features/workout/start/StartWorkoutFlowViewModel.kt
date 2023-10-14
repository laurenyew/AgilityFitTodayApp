package com.laurenyew.agilityfittodayapp.features.workout.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.laurenyew.agilityfittodayapp.data.models.WorkoutItemSeqTiming
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.features.workout.execute.WorkoutExecutionState
import com.laurenyew.agilityfittodayapp.repository.WorkoutRepository
import com.laurenyew.agilityfittodayapp.utils.CountDownTimerWithPauseResume
import com.laurenyew.agilityfittodayapp.utils.DateTimeFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    private var _selectedWorkoutItems: List<WorkoutItemSeqTiming> = emptyList()

    // Get first sequences
    val workoutSequences: Flow<PagingData<WorkoutSequence>> =
        workoutRepository.getWorkoutSequences()
            .cachedIn(viewModelScope)

    private var _workoutState =
        MutableStateFlow(WorkoutExecutionState.NOT_STARTED)
    val workoutState: StateFlow<WorkoutExecutionState> = _workoutState

    private var _currentWorkoutItemIndex = MutableStateFlow(0)
    val currentWorkoutItemIndex: StateFlow<Int> = _currentWorkoutItemIndex

    private lateinit var countDownTimer: CountDownTimerWithPauseResume
    private val _countDownTimeFlow = MutableStateFlow("")
    val countDownTimeFlow: StateFlow<String> = _countDownTimeFlow
    private val _totalTimeSinceFirstStartFlow = MutableStateFlow("")
    val totalTimeSinceFirstStartFlow = _totalTimeSinceFirstStartFlow

    init {
        selectedWorkout
            .onEach {
                _selectedWorkoutItems = it?.workoutItemSeqTiming() ?: emptyList()
                _currentWorkoutItemIndex.value = 0
            }
            .launchIn(viewModelScope)
    }

    // Workout Execution
    fun updateWorkoutState(newState: WorkoutExecutionState) {
        _workoutState.value = newState
        when (newState) {
            WorkoutExecutionState.NOT_STARTED -> {
                // Setup the timer UI
                selectedWorkout.value?.estimatedTime()?.let {
                    _countDownTimeFlow.value = DateTimeFormatter.timeInMillisToDuration(it)
                }
                _totalTimeSinceFirstStartFlow.value = DateTimeFormatter.timeInMillisToDuration(0L)
            }

            WorkoutExecutionState.RESTARTED -> restartWorkout()
            WorkoutExecutionState.IN_PROGRESS -> resumeWorkout()
            WorkoutExecutionState.STOPPED -> pauseWorkout()
            WorkoutExecutionState.READY_TO_FINISH -> prepareToFinishWorkout()
            WorkoutExecutionState.COMPLETED -> finishWorkout()
            WorkoutExecutionState.CANCELLED -> finishWorkout(isCancelled = true)
        }
    }

    private fun restartWorkout() {
        _currentWorkoutItemIndex.value = 0
        countDownTimer = CountDownTimerWithPauseResume(
            workoutTime = selectedWorkout.value?.estimatedTime() ?: 0L,
            countDownInterval = 1000L,
            onIntervalTick = { timerData ->
                updateWorkoutExecutionIndex(timerData.timePassedInWorkout)
                _countDownTimeFlow.value =
                    DateTimeFormatter.timeInMillisToDuration(timerData.timeUntilFinished)
                _totalTimeSinceFirstStartFlow.value =
                    DateTimeFormatter.timeInMillisToDuration(timerData.totalTimePassedSinceFirstStart)
            },
            onCountDownComplete = {
                updateWorkoutState(WorkoutExecutionState.READY_TO_FINISH)
            }
        )
    }

    private fun pauseWorkout() {
        countDownTimer.pause()
    }

    private fun resumeWorkout() {
        countDownTimer.resume()
    }

    private fun prepareToFinishWorkout() {
        // TODO -- Want dialog popup to confirm finish
    }

    private fun finishWorkout(isCancelled: Boolean = false) {
        navManager.updateNavRoute(StartWorkoutNavRoutes.CompletedWorkout)
    }

    private fun updateWorkoutExecutionIndex(workoutSeqTimePassed: Long) {
        val index = _selectedWorkoutItems.indexOfFirst {
            it.sequenceTiming >= workoutSeqTimePassed
        }
        _currentWorkoutItemIndex.value = index
    }

    // Flow Navigation

    /**
     * Select a Workout to Preview
     */
    fun onSelectWorkoutSequence(sequenceId: Long) {
        viewModelScope.launch {
            _selectedWorkout.value = workoutRepository.getWorkoutSequence(sequenceId)
            navManager.updateNavRoute(StartWorkoutNavRoutes.ExecuteWorkout)
            updateWorkoutState(WorkoutExecutionState.NOT_STARTED)
        }
    }

    fun onResetWorkoutSelection() {
        viewModelScope.launch {
            _selectedWorkout.value = null
            _currentWorkoutItemIndex.value = 0
            _countDownTimeFlow.value = ""
            _totalTimeSinceFirstStartFlow.value = ""
            _workoutState.value = WorkoutExecutionState.NOT_STARTED
        }
    }
}
