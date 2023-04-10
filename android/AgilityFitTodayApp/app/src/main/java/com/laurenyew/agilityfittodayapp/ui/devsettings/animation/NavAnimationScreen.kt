package com.laurenyew.agilityfittodayapp.ui.devsettings.animation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    Column {
        RowOfBoxes(numBoxes = 1)
        RowOfBoxes(numBoxes = 3, boxColor = Color.DarkGray)
        RowOfBoxes(numBoxes = 2, boxColor = Color.Red)
    }
}

@Composable
fun RowOfBoxes(
    numBoxes: Int,
    boxColor: Color = Color.Blue,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Row(modifier = modifier.padding(10.dp)) {
        for (index in 1..numBoxes) {
            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),

                colors = ButtonDefaults.buttonColors(
                    backgroundColor = boxColor,
                    contentColor = Color.White
                ),

                onClick = {
                    openNavActivity(context = context, color = boxColor, screenNum = index)
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