package com.laurenyew.agilityfittodayapp.features.workout.manager

import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.features.workout.execute.WorkoutExecutionState
import kotlinx.coroutines.flow.StateFlow

/**
 * Handle executing the workout
 */
interface ExecuteWorkoutManagerAPI : ExecuteWorkoutManagerStateAPI {
    fun setSelectedWorkout(sequence: WorkoutSequence?)
    fun updateWorkoutState(newState: WorkoutExecutionState)
    fun onResetWorkoutSelection()
    fun setUsingWorkoutTimer(shouldUseWorkoutTimer: Boolean)
}

interface ExecuteWorkoutManagerStateAPI {
    val selectedWorkoutStateFlow: StateFlow<WorkoutSequence?>
    val workoutState: StateFlow<WorkoutExecutionState>
    val currentWorkoutItemIndex: StateFlow<Int>
    val countDownTimeFlow: StateFlow<String>
    val totalTimeSinceFirstStartFlow: StateFlow<String>
    val usingWorkoutTimer: Boolean
}