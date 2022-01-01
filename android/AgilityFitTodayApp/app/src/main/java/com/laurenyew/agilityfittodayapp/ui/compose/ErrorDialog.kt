package com.laurenyew.agilityfittodayapp.ui.compose

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorDialog(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: (() -> Unit)?
) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Error") },
        text = { Text(message) },
        buttons = {
            Button(onClick = { onClickRetry?.invoke() }) {
                Text(text = "Retry")
            }
        },
        modifier = modifier
    )
}