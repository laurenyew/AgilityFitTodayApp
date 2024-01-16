package com.laurenyew.agilityfittodayapp.features.workout.manager

import com.laurenyew.agilityfittodayapp.data.models.WorkoutItemSeqTiming
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.features.workout.execute.WorkoutExecutionState
import com.laurenyew.agilityfittodayapp.utils.CountDownTimerWithPauseResume
import com.laurenyew.agilityfittodayapp.utils.DateTimeFormatter
import com.laurenyew.agilityfittodayapp.utils.TimePassedTimer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Implements [ExecuteWorkoutManagerAPI]
 */
class ExecuteWorkoutManagerImpl : ExecuteWorkoutManagerAPI {
    private var selectedWorkout = MutableStateFlow<WorkoutSequence?>(null)
    override val selectedWorkoutStateFlow: StateFlow<WorkoutSequence?> = selectedWorkout
    private var selectedWorkoutItems: List<WorkoutItemSeqTiming> = emptyList()

    private var _workoutState =
        MutableStateFlow(WorkoutExecutionState.NOT_STARTED)
    override val workoutState: StateFlow<WorkoutExecutionState> = _workoutState

    private var _currentWorkoutItemIndex = MutableStateFlow(0)
    override val currentWorkoutItemIndex: StateFlow<Int> = _currentWorkoutItemIndex

    private val _countDownTimeFlow = MutableStateFlow("")
    override val countDownTimeFlow: StateFlow<String> = _countDownTimeFlow
    private val _totalTimeSinceFirstStartFlow = MutableStateFlow("")
    override val totalTimeSinceFirstStartFlow = _totalTimeSinceFirstStartFlow

    private var _usingWorkoutTimer: Boolean = true
    override val usingWorkoutTimer: Boolean
        get() = _usingWorkoutTimer

    private var countDownTimer: CountDownTimerWithPauseResume? = null
    private var timePassedTimer: TimePassedTimer? = null

    // Workout Execution

    override fun setSelectedWorkout(sequence: WorkoutSequence?) {
        selectedWorkout.value = sequence
        selectedWorkoutItems = sequence?.workoutItemSeqTiming() ?: emptyList()
    }

    override fun updateWorkoutState(newState: WorkoutExecutionState) {
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
            WorkoutExecutionState.CANCELLED, WorkoutExecutionState.COMPLETED -> {
                cancelWorkout()
            }
        }
    }

    override fun setUsingWorkoutTimer(shouldUseWorkoutTimer: Boolean) {
        _usingWorkoutTimer = shouldUseWorkoutTimer
    }

    private fun restartWorkout() {
        _currentWorkoutItemIndex.value = 0
        if (usingWorkoutTimer) {
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
        } else {
            timePassedTimer = TimePassedTimer(
                countDownInterval = 1000L,
                onIntervalTick = { timerData ->
                    updateWorkoutExecutionIndex(timerData.timePassedInWorkout)
                    _totalTimeSinceFirstStartFlow.value =
                        DateTimeFormatter.timeInMillisToDuration(timerData.totalTimePassedSinceFirstStart)
                },
            )
        }
    }

    private fun pauseWorkout() {
        countDownTimer?.pause()
        timePassedTimer?.pause()
    }

    private fun resumeWorkout() {
        countDownTimer?.resume()
        timePassedTimer?.resume()
    }

    private fun cancelWorkout() {
        countDownTimer?.cancel()
        countDownTimer = null
        timePassedTimer?.cancel()
        timePassedTimer = null
        _currentWorkoutItemIndex.value = 0
        _countDownTimeFlow.value = ""
        _totalTimeSinceFirstStartFlow.value = ""
    }

    private fun prepareToFinishWorkout() {
        // TODO -- Want dialog popup to confirm finish
    }

    private fun updateWorkoutExecutionIndex(workoutSeqTimePassed: Long) {
        val index = selectedWorkoutItems.indexOfFirst {
            it.sequenceTiming >= workoutSeqTimePassed
        }
        _currentWorkoutItemIndex.value = index
    }

    override fun onResetWorkoutSelection() {
        cancelWorkout()
        _workoutState.value = WorkoutExecutionState.NOT_STARTED
    }
}
