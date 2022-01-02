package com.laurenyew.agilityfittodayapp.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence

@Composable
fun WorkoutSequenceListItem(
    item: WorkoutSequence,
    onItemClicked: (Long) -> Unit,
    onItemFavorited: ((Boolean) -> Unit)? = null
) {
    val isFavorite = remember(item) {
        mutableStateOf(item.isFavorite)
    }

    ListItemComposable(
        title = item.name,
        description = item.description,
        isFavorite = onItemFavorited?.let { isFavorite },
        onItemFavorited = onItemFavorited,
        modifier = Modifier
            .clickable(onClick = { onItemClicked(item.id) })
    )
}