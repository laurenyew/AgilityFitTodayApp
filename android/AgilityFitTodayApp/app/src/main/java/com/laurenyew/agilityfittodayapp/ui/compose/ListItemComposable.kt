package com.laurenyew.agilityfittodayapp.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laurenyew.agilityfittodayapp.R

@Composable
fun ListItemComposable(
    title: String,
    description: String? = null,
    @DrawableRes iconId: Int? = null,
    largeText: String? = null,
    timing: String? = null,
    isFavorite: State<Boolean>? = null,
    onItemFavorited: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            largeText?.let {
                Text(
                    largeText,
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .width(72.dp)
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            val imageModifier = Modifier
                .size(72.dp)
                .padding(8.dp)
                .fillMaxSize()

            iconId?.let {
                Image(
                    painter = painterResource(id = R.drawable.ic_run),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    colorFilter = ColorFilter.tint(Color.Black),
                    alignment = Alignment.Center,
                    modifier = imageModifier
                )
            }

            val alignment =
                if (description != null) {
                    Alignment.CenterVertically
                } else {
                    Alignment.Bottom
                }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .align(alignment)
            ) {
                if (description != null) {
                    Text(text = title, style = MaterialTheme.typography.button)
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(description)
                } else {
                    Text(
                        title,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            isFavorite?.let {
                Spacer(modifier = Modifier.width(5.dp))
                FavoriteButton(
                    isFavorite = isFavorite.value,
                    onItemFavorited = onItemFavorited,
                    modifier = imageModifier
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            timing?.let {
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    timing,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.align(Alignment.Bottom)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview
@Composable
fun ListItemComposablePreview() {
    ListItemComposable(title = "Test Title", description = "Test Description")
}

@Preview
@Composable
fun ListItemLargeTextComposablePreview() {
    ListItemComposable(title = "Test Title", description = "Test Description", largeText = "10")
}

@Preview
@Composable
fun ListItemComposableFavoritePreview() {
    ListItemComposable(
        title = "Test Title",
        description = "Test Description",
        isFavorite = remember { mutableStateOf(true) }
    )
}

@Preview
@Composable
fun ListItemWithIconComposablePreview() {
    ListItemComposable(
        title = "Test Title",
        description = "Test Description",
        iconId = R.drawable.ic_run
    )
}

@Preview
@Composable
fun ListItemWithIconAndLargeTextComposablePreview() {
    ListItemComposable(
        title = "Test Title",
        description = "Test Description",
        iconId = R.drawable.ic_run,
        largeText = "10"
    )
}

@Preview
@Composable
fun ListItemWithIconFavoriteComposablePreview() {
    ListItemComposable(
        title = "Test Title",
        description = "Test Description",
        iconId = R.drawable.ic_run,
        isFavorite = remember { mutableStateOf(true) }
    )
}

@Preview
@Composable
fun ListItemWithLargeTextAndTimingComposablePreview() {
    ListItemComposable(
        title = "Test Title",
        largeText = "10",
        timing = "10:00"
    )
}

@Composable
fun Header(title: String) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = MaterialTheme.typography.h1.color,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
        )
    }
}

@Preview
@Composable
fun HeaderPreview() {
    Header(title = "Test")
}
