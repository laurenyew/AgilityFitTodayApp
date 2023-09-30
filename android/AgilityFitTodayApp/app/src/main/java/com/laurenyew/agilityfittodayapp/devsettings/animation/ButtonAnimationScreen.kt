package com.laurenyew.agilityfittodayapp.devsettings.animation

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

/**
 * Animation screens showing button animations
 */
@OptIn(ExperimentalUnitApi::class)
@Composable
fun ButtonAnimationScreen() {
    var isButtonRoundAndLarge by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isButtonRoundAndLarge, label = "button_shape")
    val borderRadius by transition.animateInt(
        transitionSpec = { tween(durationMillis = 2000) },
        label = "border_radius",
        targetValueByState = { isRound ->
            if (isRound) 100 else 0
        }
    )
    val buttonWidth by transition.animateDp(
        transitionSpec = { tween(durationMillis = 2000) },
        label = "button_width",
        targetValueByState = { isLarge ->
            if (isLarge) 350.dp else 200.dp
        }
    )
    val buttonHeight by transition.animateDp(
        transitionSpec = { tween(durationMillis = 2000) },
        label = "button_height",
        targetValueByState = { isLarge ->
            if (isLarge) 200.dp else 100.dp
        }
    )
    val textSize = transition.animateFloat(
        transitionSpec = { tween(durationMillis = 2000) },
        label = "text_size",
        targetValueByState = { isLarge ->
            if (isLarge) 32f else 16f
        }
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            modifier = Modifier
                .width(buttonWidth)
                .height(buttonHeight),
            shape = RoundedCornerShape(percent = borderRadius),
            onClick = {
                isButtonRoundAndLarge = !isButtonRoundAndLarge
            }
        ) {
            Text(
                "Toggle Shape",
                fontSize = TextUnit(textSize.value, TextUnitType.Sp)
            )
        }
    }
}

@Preview
@Composable
fun ButtonAnimationScreen_Preview() {
    ButtonAnimationScreen()
}
