package com.laurenyew.agilityfittodayapp.features.workout.start

sealed class StartWorkoutNavRoutes(val route: String) {
    object SelectWorkout : StartWorkoutNavRoutes("Select a Workout")
    object ExecuteWorkout : StartWorkoutNavRoutes("Workout in Progress")
    object CompletedWorkout : StartWorkoutNavRoutes("Workout Complete!")
}
