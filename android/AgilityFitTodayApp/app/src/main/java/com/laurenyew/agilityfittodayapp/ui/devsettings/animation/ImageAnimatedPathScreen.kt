package com.laurenyew.agilityfittodayapp.ui.devsettings.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laurenyew.agilityfittodayapp.R
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin

/**
 * Animation screen showing image animation along a set path
 */
@Composable
fun ImageAnimatedPathScreen() {
    var isRunning by remember {
        mutableStateOf(false)
    }
    var xState by remember { mutableStateOf(0) }
    val xOffset = animateIntAsState(
        targetValue = xState,
        animationSpec =
        if (xState > 0) {
            tween(durationMillis = 4000, easing = LinearEasing)
        } else {
            snap(0)
        },
        label = "xOffset",
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Show Image Animation along a path (using offsets)")
        if (isRunning) {
            Button(
                onClick = {
                    xState = 0
                    isRunning = false
                }) {
                Text("Restart")
            }
        } else {
            Button(
                onClick = {
                    xState = 300
                    isRunning = true
                }) {
                Text("Go!")
            }
        }


        HillRunning(
            modifier = Modifier.fillMaxWidth(.8f),
            xOffset = xOffset.value,
            amplitude = 50f,
            period = 200f,
        ) { RunningImage() }
    }
}

@Composable
fun HillRunning(
    modifier: Modifier,
    xOffset: Int,
    amplitude: Float,
    period: Float,
    getYOffset: (Int, Float, Float) -> Float = calcY,
    getSlope: (Int, Float, Float) -> Float = calcSlope,
    item: @Composable () -> Unit
) {
    // outer box
    Box(modifier) {
        // inner box
        Box(
            Modifier
                .offset(
                    x = xOffset.dp,
                    y = getYOffset(xOffset, amplitude, period).dp
                )
                .rotate(calcRotation(getSlope(xOffset, amplitude, period)))
                .align(Alignment.CenterStart)
        ) {
            item()
        }
    }
}

@Composable
fun RunningImage() {
    Image(
        painter = painterResource(id = R.drawable.baseline_run),
        contentDescription = null,
        modifier = Modifier
//            .rotate(90f)
            .size(75.dp),
        colorFilter = ColorFilter.tint(Color.Blue)
    )
}

@Preview
@Composable
fun ImageAnimatedPathScreen_Preview() {
    ImageAnimatedPathScreen()
}


/// Calculate Y
const val TWO_PI = 2 * PI

val calcY: (Int, Float, Float) -> Float = { x, amplitude, period ->
    (sin(x * TWO_PI / period) * amplitude).toFloat()
}

/// Calculate Rotation
const val RADIANS_TO_DEGREES_RATIO = 360f / TWO_PI
val calcSlope: (Int, Float, Float) -> Float = { x, amplitude, period ->
    (
            cos((TWO_PI * x / period)) * (amplitude * (TWO_PI) / period)
            ).toFloat()
}

val calcRotation: (Float) -> Float = { slope ->
    (atan(slope) * RADIANS_TO_DEGREES_RATIO).toFloat()
}