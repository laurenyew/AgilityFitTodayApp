package com.laurenyew.agilityfittodayapp.ui.startWorkout

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class StartWorkoutNavRoutes(val route: String) {
    object SelectWorkout : StartWorkoutNavRoutes("Select Workout Sequence to Start Workout")
}

@Composable
fun StartWorkoutScreenNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = StartWorkoutNavRoutes.SelectWorkout.route
    ) {
        composable(StartWorkoutNavRoutes.SelectWorkout.route) {
            val searchViewModel = hiltViewModel<StartWorkoutViewModel>()
            WorkoutPickerScreen(
                viewModel = searchViewModel
            )
        }
    }
}