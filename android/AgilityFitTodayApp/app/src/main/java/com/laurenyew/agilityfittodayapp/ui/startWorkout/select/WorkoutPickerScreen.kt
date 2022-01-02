package com.laurenyew.agilityfittodayapp.ui.startWorkout.select

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
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
import com.laurenyew.agilityfittodayapp.ui.compose.Header
import com.laurenyew.agilityfittodayapp.ui.compose.WorkoutSequenceListItem
import com.laurenyew.agilityfittodayapp.ui.startWorkout.StartWorkoutViewModel
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagingWorkoutSequenceList(
    workoutSequences: Flow<PagingData<WorkoutSequence>>,
    onItemClicked: (id: Long) -> Unit,
) {
    val workoutSeqItems = workoutSequences.collectAsLazyPagingItems()

    LazyColumn {

        var lastWorkoutSequence: WorkoutSequence? = null

        for (index in 0 until workoutSeqItems.itemCount) {
            // Add sticky header
            val workoutSeq = workoutSeqItems.peek(index)
            workoutSeq?.let {
                if (lastWorkoutSequence?.workoutType != workoutSeq.workoutType)
                    stickyHeader { Header(workoutSeq.workoutType.toString()) }
            }

            // Show list item
            item {
                val workoutSeqItem = workoutSeqItems[index]
                workoutSeqItem?.let {
                    WorkoutSequenceListItem(
                        item = workoutSeqItem,
                        onItemClicked = { id -> onItemClicked(id) },
                    )
                }
            }

            lastWorkoutSequence = workoutSeq
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