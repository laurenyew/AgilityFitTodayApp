package com.laurenyew.agilityfittodayapp.ui.startWorkout.execute

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.laurenyew.agilityfittodayapp.R
import com.laurenyew.agilityfittodayapp.ui.startWorkout.StartWorkoutFlowViewModel

@Composable
fun ExecuteWorkoutScreen(viewModel: StartWorkoutFlowViewModel = hiltViewModel()) {
    val selectedWorkoutState = viewModel.selectedWorkout.collectAsState(initial = null)
    selectedWorkoutState.value?.let { selectedWorkout ->

        // TODO: Handle FAB State changes
        // TODO: Countdown Timer
        // TODO: Countdown go thru and mark parts as done
        // TODO: Skip function?

        val workoutState by viewModel.workoutState.collectAsState()

        Scaffold(
            floatingActionButton = {
                ExecuteWorkoutControls(
                    workoutState = workoutState,
                    updateWorkoutState = {
                        viewModel.updateWorkoutState(it)
                    })
            },
            floatingActionButtonPosition = FabPosition.Center,
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                WorkoutSequenceDetailCard(selectedWorkout)
                WorkoutSequenceItemsSection(selectedWorkout)
            }
        }

    }
}

@Composable
fun ExecuteWorkoutControls(
    workoutState: WorkoutExecutionState,
    updateWorkoutState: (WorkoutExecutionState) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        when (workoutState) {
            WorkoutExecutionState.NOT_STARTED ->
                ExecuteWorkoutFAB(fabTitle = "Start", fabIcon = R.drawable.ic_play) {
                    updateWorkoutState(WorkoutExecutionState.IN_PROGRESS)
                }

            WorkoutExecutionState.IN_PROGRESS ->
                ExecuteWorkoutFAB(fabTitle = "Pause", fabIcon = R.drawable.ic_pause) {
                    updateWorkoutState(WorkoutExecutionState.STOPPED)
                }

            WorkoutExecutionState.STOPPED -> {
                ExecuteWorkoutFAB(fabTitle = "Resume", fabIcon = R.drawable.ic_play) {
                    updateWorkoutState(WorkoutExecutionState.IN_PROGRESS)
                }

                Spacer(Modifier.width(30.dp))
                ExecuteWorkoutFAB(fabTitle = "Finish", fabIcon = R.drawable.ic_flag) {
                    updateWorkoutState(WorkoutExecutionState.CANCELLED)
                }
            }

            else -> {
                /** Do nothing **/
            }
        }
    }
}

@Composable
fun ExecuteWorkoutFAB(
    fabTitle: String,
    @DrawableRes fabIcon: Int,
    onFABClicked: () -> Unit
) {
    FloatingActionButton(
        onClick = { onFABClicked() },
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            val imageModifier = Modifier
                .size(36.dp)
                .fillMaxSize()

            Image(
                painter = painterResource(id = fabIcon),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center,
                modifier = imageModifier
            )

            Text(
                text = fabTitle,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 21.sp,
                style = MaterialTheme.typography.button,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun ExecuteWorkoutControls_Running_Preview() {
    Column {
        ExecuteWorkoutControls(
            workoutState = WorkoutExecutionState.IN_PROGRESS,
            updateWorkoutState = {},
        )
    }
}

@Preview
@Composable
fun ExecuteWorkoutControls_Paused_Preview() {
    Column {
        ExecuteWorkoutControls(
            workoutState = WorkoutExecutionState.STOPPED,
            updateWorkoutState = {},
        )
    }
}