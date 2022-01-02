package com.laurenyew.agilityfittodayapp.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.laurenyew.agilityfittodayapp.ui.theme.AgilityFitTodayTheme

@Composable
fun BaseActivityScreen(
    title: State<String>,
    onBackButtonPressed: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    AgilityFitTodayTheme {

        Scaffold(
            modifier = modifier,
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            "Back Button",
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    onBackButtonPressed()
                                })
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = title.value,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .alpha(1f)
                        )
                    }
                }
            },
            content = {
                content()
            }
        )
    }
}