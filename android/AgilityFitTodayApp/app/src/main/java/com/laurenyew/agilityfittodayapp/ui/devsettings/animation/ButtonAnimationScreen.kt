package com.laurenyew.agilityfittodayapp.ui.devsettings.animation

import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Animation screens showing button animations
 */
@Composable
fun ButtonAnimationScreen() {
    var isButtonRound by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isButtonRound, label = "button_shape")
    val borderRadius by transition.animateInt(
        transitionSpec = { tween(durationMillis = 2000) },
        label = "border_radius",
        targetValueByState = { isRound ->
            if (isRound) 100 else 0
        }
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            shape = RoundedCornerShape(percent = borderRadius),
            onClick = {
                isButtonRound = !isButtonRound
            }) {
            Text("Toggle Shape")
        }
    }

}

@Preview
@Composable
fun ButtonAnimationScreen_Preview() {
    ButtonAnimationScreen()
}