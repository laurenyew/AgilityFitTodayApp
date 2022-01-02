package com.laurenyew.agilityfittodayapp.ui.startWorkout.select

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.laurenyew.agilityfittodayapp.ui.compose.ErrorDialog
import com.laurenyew.agilityfittodayapp.ui.compose.WorkoutSequenceListItem
import com.laurenyew.agilityfittodayapp.ui.startWorkout.StartWorkoutViewModel

@Composable
fun StartWorkoutDetailScreen(
    viewModel: StartWorkoutViewModel = hiltViewModel()
) {
    val selectedWorkoutState = viewModel.selectedWorkout.collectAsState(initial = null)

    val selectedWorkout = selectedWorkoutState.value
    if (selectedWorkout != null) {
        Column {
            Card(elevation = 2.dp) {
                WorkoutSequenceListItem(
                    item = selectedWorkout,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    } else {
        ErrorDialog(message = "Invalid Workout Selected. Please try again.") {
            viewModel.onBackPressed()
        }
    }
}

@Preview
@Composable
fun StartWorkoutDetailScreenPreview() {
    StartWorkoutDetailScreen()
}