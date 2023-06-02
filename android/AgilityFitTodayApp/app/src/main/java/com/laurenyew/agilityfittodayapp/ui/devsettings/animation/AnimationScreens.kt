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
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

/**
 * Build the animation navigation
 */
fun NavGraphBuilder.animationDevSettingsGraph(
    navController: NavController,
    updateAppBarTitle: ((String) -> Unit)
) {
    navigation(startDestination = "animation_landing", route = "animation") {
        composable("animation_landing") {
            updateAppBarTitle("DevSettings > Animations")
            AnimationLandingScreen(
                onNavigateToImageAnimatedPathScreen = {
                    navController.navigate("image_animated_path")
                },
                onNavigateToButtonAnimationsScreen = {
                    navController.navigate("button_animations")
                },
                onNavigateToNavAnimationsScreen = {
                    navController.navigate("nav_animations")
                }
            )
        }
        composable("image_animated_path") {
            updateAppBarTitle("Animations > Image Path")
            ImageAnimatedPathScreen()
        }
        composable("button_animations") {
            updateAppBarTitle("Animations > Buttons")
            ButtonAnimationScreen()
        }
        composable("nav_animations") {
            updateAppBarTitle("Animations > Navigation")
            NavAnimationScreen()
        }
    }
}

@Composable
fun AnimationLandingScreen(
    onNavigateToImageAnimatedPathScreen: (() -> Unit),
    onNavigateToButtonAnimationsScreen: (() -> Unit),
    onNavigateToNavAnimationsScreen: (() -> Unit)
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
            onNavigateToNavAnimationsScreen()
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
        onNavigateToNavAnimationsScreen = { }
    )
}
