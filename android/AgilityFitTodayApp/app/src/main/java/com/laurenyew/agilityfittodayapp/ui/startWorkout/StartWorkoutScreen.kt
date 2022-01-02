package com.laurenyew.agilityfittodayapp.ui.startWorkout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.laurenyew.agilityfittodayapp.ui.compose.BaseActivityScreen
import com.laurenyew.agilityfittodayapp.ui.startWorkout.select.WorkoutPickerScreen

@Composable
fun StartWorkoutScreen(
    onBackButtonPressed: () -> Unit
) {
    val navController = rememberNavController()
    val titleState = remember {
        navController.currentDestination?.route ?: StartWorkoutNavRoutes.SelectWorkout.route
    }

    BaseActivityScreen(
        title = titleState,
        onBackButtonPressed = { onBackButtonPressed() })
    {
        StartWorkoutNavHost(navController)
    }
}

sealed class StartWorkoutNavRoutes(val route: String) {
    object SelectWorkout : StartWorkoutNavRoutes("Select Workout Sequence")
}

@Composable
fun StartWorkoutNavHost(
    navController: NavHostController,
) {
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