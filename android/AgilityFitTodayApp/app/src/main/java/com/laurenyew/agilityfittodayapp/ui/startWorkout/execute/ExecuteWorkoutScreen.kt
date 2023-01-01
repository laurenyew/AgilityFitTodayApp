package com.laurenyew.agilityfittodayapp.ui.startWorkout.execute

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.laurenyew.agilityfittodayapp.R
import com.laurenyew.agilityfittodayapp.ui.startWorkout.StartWorkoutFlowViewModel
import com.laurenyew.agilityfittodayapp.ui.startWorkout.select.WorkoutSequenceDetailCard
import com.laurenyew.agilityfittodayapp.ui.startWorkout.select.WorkoutSequenceItemsSection

@Composable
fun ExecuteWorkoutScreen(viewModel: StartWorkoutFlowViewModel = hiltViewModel()) {
    val selectedWorkoutState = viewModel.selectedWorkout.collectAsState(initial = null)
    selectedWorkoutState.value?.let { selectedWorkout ->

        // TODO: Handle FAB State changes
        // TODO: Countdown Timer
        // TODO: Countdown go thru and mark parts as done
        // TODO: Skip function?

        Scaffold(
            floatingActionButton = {
                ExecuteWorkoutFAB(fabState = ExecuteWorkoutFABState.PAUSE) {
                    viewModel.onStartWorkoutFromPreviewScreen()
                }
            }
        ) {
            Column {
                WorkoutSequenceDetailCard(selectedWorkout)
                WorkoutSequenceItemsSection(selectedWorkout)
            }
        }

    }
}

@Composable
fun ExecuteWorkoutFAB(
    fabState: ExecuteWorkoutFABState,
    onFABClicked: (ExecuteWorkoutFABState) -> Unit
) {
    val fabTitle: String
    @DrawableRes val fabIcon: Int
    when (fabState) {
        ExecuteWorkoutFABState.PAUSE -> {
            fabTitle = "Pause"
            fabIcon = R.drawable.ic_pause
        }
        ExecuteWorkoutFABState.RESUME -> {
            fabTitle = "Resume"
            fabIcon = R.drawable.ic_play
        }
        ExecuteWorkoutFABState.FINISH -> {
            fabTitle = "Finish"
            fabIcon = R.drawable.ic_flag
        }
    }

    FloatingActionButton(
        onClick = { onFABClicked(fabState) },
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            val imageModifier = Modifier
                .size(36.dp)
                .fillMaxSize()

            Image(
                painter = painterResource(id = fabIcon),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center,
                modifier = imageModifier
            )

            Text(
                text = fabTitle,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 21.sp,
                style = MaterialTheme.typography.button,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun ExecuteWorkoutFABPreview() {
    ExecuteWorkoutFAB(
        fabState = ExecuteWorkoutFABState.PAUSE
    ) {
    }
}