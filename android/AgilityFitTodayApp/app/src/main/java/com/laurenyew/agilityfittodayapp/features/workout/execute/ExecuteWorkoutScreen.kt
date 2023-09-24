package com.laurenyew.agilityfittodayapp.features.workout.execute

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.laurenyew.agilityfittodayapp.features.workout.start.StartWorkoutFlowViewModel

@Composable
fun ExecuteWorkoutScreen(viewModel: StartWorkoutFlowViewModel = hiltViewModel()) {
    val selectedWorkoutState = viewModel.selectedWorkout.collectAsState(initial = null)
    selectedWorkoutState.value?.let { selectedWorkout ->

        // TODO: Countdown go thru and mark parts as done
        // TODO: Skip function?
        // TODO: Make workout parts show progress

        val workoutState by viewModel.workoutState.collectAsState()
        var shouldShowPreviewWorkoutDetail by remember {
            mutableStateOf(false)
        }

        Scaffold(
            floatingActionButton = {
                ExecuteWorkoutControls(
                    workoutState = workoutState,
                    updateWorkoutState = {
                        viewModel.updateWorkoutState(it)
                    }
                )
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                WorkoutSequenceDetailCard(
                    selectedWorkout,
                    viewModel.countDownTimeFlow,
                    isPreview = shouldShowPreviewWorkoutDetail
                )
                WorkoutSequenceItemsSection(
                    selectedWorkout = selectedWorkout,
                    onScrolledPastFirstItem = { isScrolledPastFirstItem ->
                        shouldShowPreviewWorkoutDetail = isScrolledPastFirstItem
                    }
                )
            }
        }
    }
}
