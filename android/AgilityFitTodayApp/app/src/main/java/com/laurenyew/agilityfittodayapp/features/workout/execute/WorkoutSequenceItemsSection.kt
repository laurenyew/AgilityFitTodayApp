package com.laurenyew.agilityfittodayapp.features.workout.execute

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.ui.compose.WorkoutItemListItem
import com.laurenyew.agilityfittodayapp.ui.theme.gold200
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun WorkoutSequenceItemsSection(
    modifier: Modifier = Modifier,
    selectedWorkout: WorkoutSequence,
    currentExecutingItemIndexFlow: StateFlow<Int>,
    onScrolledPastFirstItem: (Boolean) -> Unit
) {
    val listState = rememberLazyListState()
    val currentExecutingItemIndex =
        currentExecutingItemIndexFlow.collectAsState()

    LazyColumn(modifier, state = listState) {
        selectedWorkout.workoutItems.forEachIndexed { index, workoutItem ->
            val hasExecuted = index < currentExecutingItemIndex.value
            val isExecuting = index == currentExecutingItemIndex.value
            val colorModifer = colorModifier(
                hasExecuted = hasExecuted,
                isExecuting = isExecuting
            )

            // Show list item
            item {
                Card(shape = RectangleShape) {
                    WorkoutItemListItem(
                        item = workoutItem,
                        shouldShowTiming = true,
                        modifier = colorModifer
                    )
                }
                Divider()
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
        hasExecuted -> Modifier.alpha(0.3f)
        isExecuting -> Modifier.background(gold200)
        else -> Modifier
    }
