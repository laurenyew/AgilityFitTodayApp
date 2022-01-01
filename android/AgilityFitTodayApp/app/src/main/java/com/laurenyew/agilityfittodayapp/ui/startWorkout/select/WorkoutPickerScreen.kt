package com.laurenyew.agilityfittodayapp.ui.startWorkout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.ui.compose.WorkoutSequenceListItem
import com.laurenyew.agilityfittodayapp.ui.theme.dividerColor
import kotlinx.coroutines.flow.Flow

@Composable
fun WorkoutPickerScreen(
    viewModel: StartWorkoutViewModel = hiltViewModel()
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        Text(
            text = "START A WORKOUT",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = "Pick a workout:",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        PagingWorkoutSequenceList(
            workoutSequences = viewModel.workoutSequences,
            onItemClicked = { viewModel.onSelectWorkoutSequence(it) },
        )
    }
}

@Composable
fun PagingWorkoutSequenceList(
    workoutSequences: Flow<PagingData<WorkoutSequence>>,
    onItemClicked: (id: Long) -> Unit,
) {
    val items = workoutSequences.collectAsLazyPagingItems()

    LazyColumn {
        items(items.itemCount) { index ->
            val item = items[index]
            item?.let {
                WorkoutSequenceListItem(
                    item = item,
                    onItemClicked = { id -> onItemClicked(id) },
                )
                Divider(color = dividerColor)
            }
        }
    }
}

@Preview
@Composable
fun WorkoutPickerScreenPreview() {
    WorkoutPickerScreen()
}