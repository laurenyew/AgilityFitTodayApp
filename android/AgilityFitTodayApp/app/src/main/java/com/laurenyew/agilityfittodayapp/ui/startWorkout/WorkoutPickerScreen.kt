package com.laurenyew.agilityfittodayapp.ui.startWorkout

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WorkoutPickerScreen() {
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
//        LazyColumn {
//            items(items.size) { index ->
//                val item = items[index]
//                ListItemComposable(
//                    item = item,
//                    onItemClicked = { id -> onItemClicked(id) },
//                    onItemFavorited = { isFavorited ->
//                        onItemFavorited(item, isFavorited)
//                    }
//                )
//                Divider(color = dividerColor)
//            }
//        }
    }
}

@Preview
@Composable
fun WorkoutPickerScreenPreview() {
    WorkoutPickerScreen()
}