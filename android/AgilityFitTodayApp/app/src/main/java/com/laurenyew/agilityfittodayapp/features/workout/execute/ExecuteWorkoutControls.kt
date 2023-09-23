package com.laurenyew.agilityfittodayapp.features.workout.execute

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExecuteWorkoutControls(
    workoutState: WorkoutExecutionState,
    updateWorkoutState: (WorkoutExecutionState) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.End
    ) {
        when (workoutState) {
            WorkoutExecutionState.NOT_STARTED ->
                ExecuteWorkoutFAB(
                    fabTitle = "Start",
                    imageVector = Icons.Filled.PlayArrow
                ) {
                    updateWorkoutState(WorkoutExecutionState.RESTARTED)
                }

            WorkoutExecutionState.RESTARTED, WorkoutExecutionState.IN_PROGRESS ->
                ExecuteWorkoutFAB(
                    fabTitle = "Pause",
                    imageVector = Icons.Filled.Pause
                ) {
                    updateWorkoutState(WorkoutExecutionState.STOPPED)
                }

            WorkoutExecutionState.STOPPED -> {
                ExecuteWorkoutFAB(
                    fabTitle = "Restart",
                    imageVector = Icons.Filled.RestartAlt
                ) {
                    updateWorkoutState(WorkoutExecutionState.RESTARTED)
                }

                Spacer(Modifier.width(5.dp))

                ExecuteWorkoutFAB(
                    fabTitle = "Resume",
                    imageVector = Icons.Filled.PlayArrow
                ) {
                    updateWorkoutState(WorkoutExecutionState.IN_PROGRESS)
                }

                Spacer(Modifier.width(5.dp))
                ExecuteWorkoutFAB(
                    fabTitle = "Finish",
                    Icons.Filled.Flag
                ) {
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
    imageVector: ImageVector,
    onFABClicked: () -> Unit
) {
    FloatingActionButton(
        onClick = { onFABClicked() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val imageModifier = Modifier
                .size(24.dp)
                .fillMaxSize()

            Icon(
                imageVector = imageVector,
                contentDescription = fabTitle,
                modifier = imageModifier,
                tint = MaterialTheme.colors.onPrimary
            )

            Text(
                text = fabTitle,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 18.sp,
                style = MaterialTheme.typography.button,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
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
            updateWorkoutState = {}
        )
    }
}

@Preview
@Composable
fun ExecuteWorkoutControls_Paused_Preview() {
    Column {
        ExecuteWorkoutControls(
            workoutState = WorkoutExecutionState.STOPPED,
            updateWorkoutState = {}
        )
    }
}
