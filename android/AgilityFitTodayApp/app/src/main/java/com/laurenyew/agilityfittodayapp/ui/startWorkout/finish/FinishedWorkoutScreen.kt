package com.laurenyew.agilityfittodayapp.ui.startWorkout.finish

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.laurenyew.agilityfittodayapp.R
import com.laurenyew.agilityfittodayapp.data.models.estimatedTimeFormattedString
import com.laurenyew.agilityfittodayapp.ui.startWorkout.StartWorkoutFlowViewModel

/**
 * Show finished workout screen
 * Congrats message
 * TODO: Share button
 * Done button, should exit activity
 * TODO: Save workout into history
 * TODO: Confetti?
 */
@Composable
fun FinishedWorkoutScreen(viewModel: StartWorkoutFlowViewModel = hiltViewModel()) {
    val activity = (LocalContext.current as? Activity)
    val imageModifier = Modifier
        .size(192.dp)
        .padding(20.dp)
        .fillMaxSize()

    val workout by viewModel.selectedWorkout.collectAsState()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center,
                modifier = imageModifier
            )
            Text(
                "You did it!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(8.dp)
            )
            workout?.let { finishedWorkout ->
                Text(
                    finishedWorkout.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Text(
                    finishedWorkout.description,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Text(
                    finishedWorkout.workoutType.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Text(
                    finishedWorkout.estimatedTimeFormattedString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(8.dp)
                )

            }

            Spacer(Modifier.height(20.dp))
            Button(onClick = {
                activity?.finish()
            }) {
                Text(
                    text = "Done",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.button,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    }
}