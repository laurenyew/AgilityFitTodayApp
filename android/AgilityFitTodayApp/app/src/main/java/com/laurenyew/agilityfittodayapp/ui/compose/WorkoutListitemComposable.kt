package com.laurenyew.agilityfittodayapp.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.laurenyew.agilityfittodayapp.R
import com.laurenyew.agilityfittodayapp.data.models.WorkoutItem
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.data.models.estimatedTimeFormattedString

@Composable
fun WorkoutSequenceListItem(
    item: WorkoutSequence,
    onItemClicked: ((Long) -> Unit)? = null,
    onItemFavorited: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val isFavorite = remember(item) {
        mutableStateOf(item.isFavorite)
    }

    ListItemComposable(
        title = item.name,
        description = item.description,
        iconId = R.drawable.ic_run,
        isFavorite = onItemFavorited?.let { isFavorite },
        onItemFavorited = onItemFavorited,
        modifier = modifier
            .clickable(onClick = { onItemClicked?.invoke(item.id) })
    )
}

@Composable
fun WorkoutItemListItem(
    item: WorkoutItem,
    shouldShowTiming: Boolean = false,
    onItemClicked: ((Long) -> Unit)? = null,
    onItemFavorited: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val isFavorite = remember(item) {
        mutableStateOf(item.isFavorite)
    }

    val quantity = item.quantity
    val quantityText = if (quantity > 1 && item.itemBase.isMeasuredInReps) {
        quantity.toString()
    } else {
        " "
    }

    ListItemComposable(
        title = item.itemBase.name,
        largeText = quantityText,
        timing = if (shouldShowTiming) item.estimatedTimeFormattedString() else null,
        isFavorite = onItemFavorited?.let { isFavorite },
        onItemFavorited = onItemFavorited,
        modifier = modifier
            .clickable(onClick = { onItemClicked?.invoke(item.id) })
    )
}
