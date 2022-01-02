package com.laurenyew.agilityfittodayapp.ui.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen() {
    val context = LocalContext.current
    val data = listOf(
        DashboardOption.Calendar,
        DashboardOption.Explore,
        DashboardOption.Schedule,
        DashboardOption.StartWorkout,
        DashboardOption.CreateWorkout,
        DashboardOption.Badges
    )
    LazyVerticalGrid(
        cells = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        this.items(data) { item ->
            Card(
                modifier = Modifier
                    .height(160.dp)
                    .padding(4.dp)
                    .clickable {
                        item.onClick(context)
                    },
                backgroundColor = Color.LightGray,
            ) {
                Box(
                    modifier = Modifier.size(350.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.name,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.button,
                        modifier = Modifier
                            .padding(24.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}