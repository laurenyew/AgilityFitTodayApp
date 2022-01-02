package com.laurenyew.agilityfittodayapp.ui.startWorkout.select

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.laurenyew.agilityfittodayapp.data.models.estimatedTimeFormattedString
import com.laurenyew.agilityfittodayapp.ui.compose.WorkoutItemListItem
import com.laurenyew.agilityfittodayapp.ui.compose.WorkoutSequenceListItem
import com.laurenyew.agilityfittodayapp.ui.startWorkout.StartWorkoutViewModel
import com.laurenyew.agilityfittodayapp.ui.theme.cardColor

@Composable
fun StartWorkoutDetailScreen(
    viewModel: StartWorkoutViewModel = hiltViewModel()
) {
    val selectedWorkoutState = viewModel.selectedWorkout.collectAsState(initial = null)

    selectedWorkoutState.value?.let { selectedWorkout ->
        val estimatedTime = selectedWorkout.estimatedTimeFormattedString()

        Column {
            Card(
                backgroundColor = cardColor,
                elevation = 2.dp
            ) {
                Column {
                    WorkoutSequenceListItem(
                        item = selectedWorkout,
                        modifier = Modifier.padding(8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp, end = 16.dp, bottom = 21.dp, top = 0.dp)
                    ) {
                        Text(
                            "Estimated Time:",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            estimatedTime,
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }

            LazyColumn {
                selectedWorkout.workoutItems.forEach { workoutItem ->
                    // Show list item
                    item {
                        Card(elevation = 2.dp) {
                            WorkoutItemListItem(
                                item = workoutItem,
                                shouldShowTiming = true,
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                colors = buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                ),
                onClick = {
                    viewModel.startWorkout()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Start!",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp, start = 32.dp, end = 32.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun StartWorkoutDetailScreenPreview() {
    StartWorkoutDetailScreen()
}