package com.laurenyew.agilityfittodayapp.features.workout.execute

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.laurenyew.agilityfittodayapp.features.workout.start.StartWorkoutFlowViewModel

@Composable
fun ExecuteWorkoutScreen(viewModel: StartWorkoutFlowViewModel = hiltViewModel()) {
    val selectedWorkoutState = viewModel.selectedWorkoutStateFlow.collectAsState(initial = null)
    selectedWorkoutState.value?.let { selectedWorkout ->

        // TODO: Countdown go thru and mark parts as done
        // TODO: Skip function?
        // TODO: Make workout parts show progress

        val workoutState by viewModel.workoutState.collectAsState()
        var shouldShowPreviewWorkoutDetail by remember {
            mutableStateOf(false)
        }
        val isReadyToFinish = workoutState == WorkoutExecutionState.READY_TO_FINISH

        Scaffold(
            floatingActionButton = {
                ExecuteWorkoutControls(
                    workoutState = workoutState,
                    updateWorkoutState = {
                        viewModel.updateWorkoutState(it)
                    },
                    setUsingWorkoutTimer = {
                        viewModel.setUsingWorkoutTimer(it)
                    }
                )
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                WorkoutSequenceDetailCard(
                    selectedWorkout,
                    viewModel.countDownTimeFlow,
                    viewModel.totalTimeSinceFirstStartFlow,
                    isPreview = shouldShowPreviewWorkoutDetail
                )
                WorkoutSequenceItemsSection(
                    selectedWorkout = selectedWorkout,
                    currentExecutingItemIndexFlow = viewModel.currentWorkoutItemIndex,
                    onScrolledPastFirstItem = { isScrolledPastFirstItem ->
                        shouldShowPreviewWorkoutDetail = isScrolledPastFirstItem
                    }
                )
            }
        }

        // Show dialog confirmation if we're ready to finish
        if (isReadyToFinish) {
            AlertDialog(
                title = {
                    Text("You did it!", style = MaterialTheme.typography.body1)
                },
                text = {
                    val annotatedString = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(selectedWorkout.name)
                        }
                        append(" completed!")
                    }
                    Text(annotatedString)
                },
                buttons = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(onClick = {
                            viewModel.updateWorkoutState(WorkoutExecutionState.RESTARTED)
                        }, modifier = Modifier.padding(8.dp)) {
                            Text(
                                "Restart",
                                style = MaterialTheme.typography.button
                            )
                        }
                        TextButton(
                            onClick = { viewModel.updateWorkoutState(WorkoutExecutionState.COMPLETED) },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                "Finish",
                                style = MaterialTheme.typography.button,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                },
                onDismissRequest = {}
            )
        }
    }
}
