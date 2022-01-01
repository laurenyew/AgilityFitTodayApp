package com.laurenyew.agilityfittodayapp.ui.navigation

sealed class NavigationRoutes(val route: String) {
    object Home : NavigationRoutes("Home")
    object Dashboard : NavigationRoutes("Dashboard")
    object StartWorkoutSelectWorkout : NavigationRoutes("Select Workout Sequence to Start Workout")
}