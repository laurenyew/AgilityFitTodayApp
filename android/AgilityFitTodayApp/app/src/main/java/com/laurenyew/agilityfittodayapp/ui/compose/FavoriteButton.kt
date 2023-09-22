package com.laurenyew.agilityfittodayapp.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    isFavorite: Boolean = false,
    onItemFavorited: ((Boolean) -> Unit)? = null,
    modifier: Modifier =
        Modifier
            .width(8.dp)
            .height(8.dp)
) {
    if (isFavorite) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorite-d",
            modifier = modifier
                .clickable { onItemFavorited?.invoke(false) }
        )
    } else {
        Icon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Not Favorite-d",
            modifier = modifier
                .width(8.dp)
                .height(8.dp)
                .clickable { onItemFavorited?.invoke(true) }
        )
    }
}

@Preview
@Composable
fun FavoriteButton_Preview() {
    FavoriteButton()
}
