package com.laurenyew.agilityfittodayapp.features.workout.execute

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.ui.compose.WorkoutItemListItem
import com.laurenyew.agilityfittodayapp.ui.theme.gold200
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun WorkoutSequenceItemsSection(
    modifier: Modifier = Modifier,
    selectedWorkout: WorkoutSequence,
    currentExecutingItemIndex: Int,
    onScrolledPastFirstItem: (Boolean) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(modifier, state = listState) {
        selectedWorkout.workoutItems.forEachIndexed { index, workoutItem ->
            val hasExecuted = index < currentExecutingItemIndex
            val isExecuting = index == currentExecutingItemIndex
            val colorModifer = colorModifier(
                hasExecuted = hasExecuted,
                isExecuting = isExecuting
            )

            // Show list item
            item {
                Card {
                    WorkoutItemListItem(
                        item = workoutItem,
                        shouldShowTiming = true,
                        modifier = colorModifer
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

/**
 * @param hasExecuted : Use disabled color
 * @param isExecuting : Use highlighted color
 * Otherwise, return default [Modifer]
 */
private fun colorModifier(hasExecuted: Boolean = false, isExecuting: Boolean = false): Modifier =
    when {
        hasExecuted -> Modifier.background(Color.Gray)
        isExecuting -> Modifier.background(gold200)
        else -> Modifier
    }
