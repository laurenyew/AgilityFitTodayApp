package com.laurenyew.agilityfittodayapp.ui.startWorkout.execute

/**
 * State of the workout during execution
 */
enum class WorkoutExecutionState {
    NOT_STARTED,
    IN_PROGRESS,
    STOPPED,
    CANCELLED,
    COMPLETED
}