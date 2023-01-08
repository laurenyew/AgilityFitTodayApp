package com.laurenyew.agilityfittodayapp.ui.startWorkout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.laurenyew.agilityfittodayapp.ui.compose.BaseActivityScreen
import com.laurenyew.agilityfittodayapp.ui.startWorkout.execute.ExecuteWorkoutScreen
import com.laurenyew.agilityfittodayapp.ui.startWorkout.select.WorkoutPickerScreen

@Composable
fun StartWorkoutScreen(
    viewModel: StartWorkoutFlowViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val currentRoute =
        viewModel.currentNavRoute.collectAsState(initial = StartWorkoutNavRoutes.SelectWorkout.route)

    BaseActivityScreen(
        title = currentRoute.value,
        onBackButtonPressed = { viewModel.onBackPressed() })
    {
        StartWorkoutNavHost(viewModel, navController)
    }
}

@Composable
fun StartWorkoutNavHost(
    viewModel: StartWorkoutFlowViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = StartWorkoutNavRoutes.SelectWorkout.route
    ) {
        composable(StartWorkoutNavRoutes.SelectWorkout.route) {
            WorkoutPickerScreen(
                viewModel = viewModel
            )
        }
        composable(StartWorkoutNavRoutes.ExecuteWorkout.route) {
            ExecuteWorkoutScreen(
                viewModel = viewModel
            )
        }
    }

    val currentRoute =
        viewModel.currentNavRoute.collectAsState(initial = StartWorkoutNavRoutes.SelectWorkout.route)

    navController.navigate(currentRoute.value) {
        popUpTo = navController.graph.startDestinationId
        launchSingleTop = true
    }
}