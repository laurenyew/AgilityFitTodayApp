package com.laurenyew.agilityfittodayapp.features.workout.execute

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.ui.compose.WorkoutItemListItem
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun WorkoutSequenceItemsSection(
    modifier: Modifier = Modifier,
    selectedWorkout: WorkoutSequence,
    onScrolledPastFirstItem: (Boolean) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(modifier, state = listState) {
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

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .map { index -> index > 0 }
            .distinctUntilChanged()
            .collect {
                onScrolledPastFirstItem(it)
            }
    }
}
