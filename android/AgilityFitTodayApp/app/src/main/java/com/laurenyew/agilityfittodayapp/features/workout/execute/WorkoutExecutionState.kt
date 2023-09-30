package com.laurenyew.agilityfittodayapp.features.workout.execute

/**
 * State of the workout during execution
 */
enum class WorkoutExecutionState {
    NOT_STARTED,
    RESTARTED,
    IN_PROGRESS,
    STOPPED,
    CANCELLED,
    READY_TO_FINISH,
    COMPLETED
}
