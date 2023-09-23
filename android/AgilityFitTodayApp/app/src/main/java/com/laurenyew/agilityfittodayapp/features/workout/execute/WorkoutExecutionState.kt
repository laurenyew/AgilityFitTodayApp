package com.laurenyew.agilityfittodayapp.features.workout.execute

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
