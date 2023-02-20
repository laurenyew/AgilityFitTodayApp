package com.laurenyew.agilityfittodayapp.ui.devsettings

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.laurenyew.agilityfittodayapp.ui.devsettings.animation.AnimationScreen

@Composable
fun DevSettingsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "landing"
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable("landing") {
                DevSettingsLandingScreen(
                    onNavigateToAnimation = { navController.navigate("animation") },
                    /*...*/
                )
            }
            composable("animation") { AnimationScreen() }
        }
    }
}

@Composable
fun DevSettingsLandingScreen(
    onNavigateToAnimation: (() -> Unit),
) {
    Button(onClick = {
        onNavigateToAnimation()
    }) {
        Text(
            text = "Animations",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.button,
            modifier = Modifier
                .padding(8.dp)
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Preview
@Composable
fun DevSettingsLandingScreen_Preview() {
    DevSettingsLandingScreen(
        onNavigateToAnimation = {}
    )
}