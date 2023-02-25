package com.laurenyew.agilityfittodayapp.ui.devsettings.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview

/**
 * Animation screens showing button animations
 */
@Composable
fun ButtonAnimationScreen() {
    var isVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Button(onClick = {
            isVisible = !isVisible
        }) {
            Text("Toggle Visibility")
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally() + fadeIn(),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(Modifier.background(Color.Red, shape = RectangleShape))
        }
//        Spacer(Modifier.height(16.dp))
//        Button(onClick = { /*TODO*/ }) {
//            Text("Toggle Shape")
//        }
    }

}

@Preview
@Composable
fun ButtonAnimationScreen_Preview() {
    ButtonAnimationScreen()
}