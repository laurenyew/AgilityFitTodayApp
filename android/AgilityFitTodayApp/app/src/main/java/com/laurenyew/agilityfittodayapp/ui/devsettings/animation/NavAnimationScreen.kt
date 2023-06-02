package com.laurenyew.agilityfittodayapp.ui.devsettings.animation

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Animation screens showing button / card animation into a new screen
 */
@Composable
fun NavAnimationScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Cyan)
        ) {
            Column {
                RowOfBoxes(numBoxes = 1)
                RowOfBoxes(numBoxes = 3, boxColor = Color.DarkGray)
                RowOfBoxes(numBoxes = 2, boxColor = Color.Red)
            }
        }
    }
}

@Composable
fun RowOfBoxes(
    numBoxes: Int,
    boxColor: Color = Color.Blue,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var isButtonFillScreen by remember { mutableStateOf(false) }
    val transition =
        updateTransition(targetState = isButtonFillScreen, label = "button_fill_screen")

    val buttonSize by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 2000) },
        label = "button_height",
        targetValueByState = { isLarge ->
            if (isLarge) 1f else .25f
        }
    )

    Row(modifier = modifier.padding(10.dp)) {
        for (index in 1..numBoxes) {
            Button(
                modifier = modifier
                    .fillMaxSize(buttonSize),

                colors = ButtonDefaults.buttonColors(
                    backgroundColor = boxColor,
                    contentColor = Color.White
                ),

                onClick = {
                    isButtonFillScreen = !isButtonFillScreen
//                    openNavActivity(context = context, color = boxColor, screenNum = index)
                }
            ) {
                Text("Navigate to Screen $index")
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

private fun openNavActivity(context: Context, color: Color, screenNum: Int) {
    val intent = Intent(context, NavActivity::class.java)
    intent.putExtra(NavActivity.COLOR_INTENT_KEY, color.toArgb())
    intent.putExtra(NavActivity.SCREEN_NUM_KEY, screenNum)
    context.startActivity(intent)
}

@Preview
@Composable
fun NavAnimationScreen_Preview() {
    NavAnimationScreen()
}
