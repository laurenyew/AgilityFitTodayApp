package com.laurenyew.agilityfittodayapp.ui.startWorkout.select

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.ui.compose.ErrorDialog
import com.laurenyew.agilityfittodayapp.ui.compose.WorkoutSequenceListItem
import com.laurenyew.agilityfittodayapp.ui.startWorkout.StartWorkoutViewModel
import com.laurenyew.agilityfittodayapp.ui.theme.dividerColor
import kotlinx.coroutines.flow.Flow

@Composable
fun WorkoutPickerScreen(
    viewModel: StartWorkoutViewModel = hiltViewModel()
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        PagingWorkoutSequenceList(
            workoutSequences =
            viewModel.workoutSequences,
            onItemClicked =
            { viewModel.onSelectWorkoutSequence(it) },
        )
    }
}

@Composable
fun PagingWorkoutSequenceList(
    workoutSequences: Flow<PagingData<WorkoutSequence>>,
    onItemClicked: (id: Long) -> Unit,
) {
    val workoutSeqItems = workoutSequences.collectAsLazyPagingItems()

    LazyColumn {
        items(workoutSeqItems.itemCount) { index ->
            val workoutSeqItem = workoutSeqItems[index]
            workoutSeqItem?.let {
                WorkoutSequenceListItem(
                    item = workoutSeqItem,
                    onItemClicked = { id -> onItemClicked(id) },
                )
            }
        }

        workoutSeqItems.apply {
            when {
                !loadState.append.endOfPaginationReached -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = workoutSeqItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorDialog(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = workoutSeqItems.loadState.append as LoadState.Error
                    item {
                        ErrorDialog(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun WorkoutPickerScreenPreview() {
    WorkoutPickerScreen()
}