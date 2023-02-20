package com.laurenyew.agilityfittodayapp.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel = hiltViewModel()) {
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        if (settingsViewModel.shouldShowDevSettingsButton) {
            Button(onClick = {
                settingsViewModel.openDevSettings(context)
            }) {
                Text(
                    text = "Dev Settings",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.button,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = { /*TODO*/ }) {
            Text(
                text = "About",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.button,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }

}

@Preview
@Composable
fun SettingsScreen_Preview() {
    SettingsScreen()
}