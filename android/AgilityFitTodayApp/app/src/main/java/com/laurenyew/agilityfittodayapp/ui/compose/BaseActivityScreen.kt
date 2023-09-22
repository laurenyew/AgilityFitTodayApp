package com.laurenyew.agilityfittodayapp.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.laurenyew.agilityfittodayapp.ui.theme.AgilityFitTodayTheme

@Composable
fun BaseActivityScreen(
    title: String,
    onBackButtonPressed: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (modifier: Modifier) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    AgilityFitTodayTheme {
        Scaffold(
            modifier = modifier,
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            "Back Button",
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    onBackButtonPressed()
                                }
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = title,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .alpha(1f)
                        )
                    }
                }
            },
            content = {
                content(modifier = Modifier.padding(it))
            }
        )
    }
}
