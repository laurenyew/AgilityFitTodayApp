package com.laurenyew.agilityfittodayapp.ui.devsettings.animation

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

@Composable
fun AnimationNavHost(
    onUpdateTopAppBarTitle: ((String) -> Unit),
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "landing"
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("landing") {
            AnimationLandingScreen(
                onNavigateToImageAnimatedPathScreen = {
                    navController.navigate("image_animated_path")
                    onUpdateTopAppBarTitle("Animations > Image Path")
                },
                onNavigateToButtonAnimationsScreen = {
                    navController.navigate("button_animations")
                    onUpdateTopAppBarTitle("Animations > Buttons")
                },
                onNavigateToNavANimationsScreen = {
                    navController.navigate("nav_animations")
                    onUpdateTopAppBarTitle("Animations > Navigation")
                }
            )
        }
        composable("image_animated_path") { ImageAnimatedPathScreen() }
        composable("button_animations") { ButtonAnimationScreen() }
        composable("nav_animations") { NavAnimationScreen() }
    }
}

@Composable
fun AnimationLandingScreen(
    onNavigateToImageAnimatedPathScreen: (() -> Unit),
    onNavigateToButtonAnimationsScreen: (() -> Unit),
    onNavigateToNavANimationsScreen: (() -> Unit)
) {
    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Button(onClick = {
            onNavigateToImageAnimatedPathScreen()
        }) {
            Text(
                text = "Animate image along path",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.button,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            onNavigateToButtonAnimationsScreen()
        }) {
            Text(
                text = "Button Animations",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.button,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            onNavigateToImageAnimatedPathScreen()
        }) {
            Text(
                text = "Navigation Animations",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.button,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun AnimationLandingScreen_Preview() {
    AnimationLandingScreen(
        onNavigateToImageAnimatedPathScreen = { },
        onNavigateToButtonAnimationsScreen = { },
        onNavigateToNavANimationsScreen = { }
    )
}