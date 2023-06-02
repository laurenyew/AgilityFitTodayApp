package com.laurenyew.agilityfittodayapp.ui.startWorkout.execute

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.data.models.estimatedTimeFormattedString
import com.laurenyew.agilityfittodayapp.ui.compose.WorkoutItemListItem
import com.laurenyew.agilityfittodayapp.ui.compose.WorkoutSequenceListItem
import com.laurenyew.agilityfittodayapp.ui.theme.cardColor

@Composable
fun WorkoutSequenceDetailCard(selectedWorkout: WorkoutSequence) {
    val estimatedTime = selectedWorkout.estimatedTimeFormattedString()

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
}

@Composable
fun WorkoutSequenceItemsSection(
    selectedWorkout: WorkoutSequence,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        selectedWorkout.workoutItems.forEach { workoutItem ->
            // Show list item
            item {
                Card {
                    WorkoutItemListItem(
                        item = workoutItem,
                        shouldShowTiming = true
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(96.dp))
        }
    }
}
