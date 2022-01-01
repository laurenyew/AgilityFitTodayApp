package com.laurenyew.agilityfittodayapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.laurenyew.agilityfittodayapp.ui.home.HomeScreen
import com.laurenyew.agilityfittodayapp.ui.startWorkout.StartWorkoutViewModel
import com.laurenyew.agilityfittodayapp.ui.startWorkout.WorkoutPickerScreen

@Composable
fun HomeScreenNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Home.route
    ) {
        composable(NavigationRoutes.Home.route) {
            HomeScreen(navController)
        }
        composable(NavigationRoutes.StartWorkoutSelectWorkout.route) {
            val searchViewModel = hiltViewModel<StartWorkoutViewModel>()
            WorkoutPickerScreen(
                viewModel = searchViewModel
            )
        }
    }
}