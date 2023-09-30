package com.laurenyew.agilityfittodayapp.features.workout.execute

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.data.models.estimatedTimeFormattedString
import com.laurenyew.agilityfittodayapp.ui.compose.WorkoutSequenceListItem
import com.laurenyew.agilityfittodayapp.ui.theme.cardColor
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WorkoutSequenceDetailCard(
    selectedWorkout: WorkoutSequence,
    countDownTimeFlow: StateFlow<String>,
    totalTimeSinceFirstStartFlow: StateFlow<String>,
    isPreview: Boolean = false
) {
    val countDownTime = countDownTimeFlow.collectAsState(initial = "")
    val totalTimeSinceFirstStart = totalTimeSinceFirstStartFlow.collectAsState(initial = "")

    Card(
        backgroundColor = cardColor,
        elevation = 2.dp
    ) {
        AnimatedContent(targetState = isPreview, label = "Workout Top Detail") {
            if (it) {
                WorkoutSequencePreviewContent(
                    selectedWorkout = selectedWorkout,
                    countDownTime = countDownTime.value,
                    totalTimeSinceFirstStart = totalTimeSinceFirstStart.value
                )
            } else {
                WorkoutSequenceDetailContent(
                    selectedWorkout = selectedWorkout,
                    countDownTime = countDownTime.value,
                    totalTimeSinceFirstStart = totalTimeSinceFirstStart.value
                )
            }
        }
    }
}

@Composable
fun WorkoutSequenceDetailContent(
    selectedWorkout: WorkoutSequence,
    countDownTime: String,
    totalTimeSinceFirstStart: String
) {
    val estimatedTime = selectedWorkout.estimatedTimeFormattedString()

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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 16.dp, bottom = 21.dp, top = 0.dp)
        ) {
            Text(
                "Time Left:",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                countDownTime,
                style = MaterialTheme.typography.h6
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 16.dp, bottom = 21.dp, top = 0.dp)
        ) {
            Text(
                "Total Time Passed:",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                totalTimeSinceFirstStart,
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
fun WorkoutSequencePreviewContent(
    selectedWorkout: WorkoutSequence,
    countDownTime: String,
    totalTimeSinceFirstStart: String
) {
    WorkoutSequenceListItem(
        item = selectedWorkout,
        description = "Time Left: $countDownTime\nTotal Time Passed: $totalTimeSinceFirstStart",
        modifier = Modifier.padding(8.dp)
    )
}
