package com.laurenyew.agilityfittodayapp.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laurenyew.agilityfittodayapp.R
import laurenyew.petadoptsampleapp.ui.theme.sectionHeader

@Composable
fun ListItemComposable(
    title: String,
    description: String,
    isFavorite: State<Boolean>? = null,
    onItemFavorited: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Card(elevation = 2.dp) {
        Row(modifier = modifier.padding(8.dp)) {
            val imageModifier = Modifier
                .size(72.dp)
                .padding(8.dp)
                .fillMaxSize()

            Image(
                painter = painterResource(id = R.drawable.ic_run),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.tint(Color.Black),
                modifier = imageModifier
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(title, style = MaterialTheme.typography.button)
                Spacer(modifier = Modifier.height(3.dp))
                Text(description)
            }

            if (isFavorite != null) {
                Spacer(modifier = Modifier.width(5.dp))
                FavoriteButton(
                    isFavorite = isFavorite.value,
                    onItemFavorited = onItemFavorited,
                    modifier = imageModifier
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
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun HeaderPreview(){
    Header(title = "Test")
}