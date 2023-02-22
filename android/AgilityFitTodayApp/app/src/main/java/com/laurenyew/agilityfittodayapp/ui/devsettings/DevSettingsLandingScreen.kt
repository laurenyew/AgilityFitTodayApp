package com.laurenyew.agilityfittodayapp.ui.devsettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.laurenyew.agilityfittodayapp.ui.devsettings.animation.AnimationNavHost

@Composable
fun DevSettingsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "landing"
) {
    var title by remember { mutableStateOf("Dev Settings") }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = title)
            }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            })

        }
    ) {
        Column(
            Modifier
                .padding(it)
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
                        onNavigateToAnimation = {
                            navController.navigate("animation")
                            title = "DevSettings > Animations"
                        },
                    )
                }
                composable("animation") {
                    AnimationNavHost(
                        onUpdateTopAppBarTitle = { newTitle: String ->
                            title = newTitle
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DevSettingsLandingScreen(
    onNavigateToAnimation: (() -> Unit)
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