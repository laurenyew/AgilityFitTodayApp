package com.laurenyew.agilityfittodayapp.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
            ) {
                Button(onClick = { onClickRetry?.invoke() }) {
                    Text(text = "Retry")
                }
            }

        },
        modifier = modifier
    )
}

@Preview
@Composable
fun ErrorDialogPreview() {
    ErrorDialog(message = "Test Error") {

    }
}