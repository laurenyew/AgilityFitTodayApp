package com.laurenyew.agilityfittodayapp.devsettings.animation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.laurenyew.agilityfittodayapp.ui.theme.AgilityFitTodayTheme

/**
 * Sample nav activity to get to from [NavAnimationScreen]
 */
class NavActivity : AppCompatActivity() {

    @OptIn(ExperimentalUnitApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val color = Color(intent.getIntExtra(COLOR_INTENT_KEY, Color.Black.toArgb()))
        val screenNum = intent.getIntExtra(SCREEN_NUM_KEY, 0)

        setContent {
            AgilityFitTodayTheme {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(color)
                        .fillMaxSize()
                ) {
                    Text(
                        "Screen: $screenNum",
                        color = Color.White,
                        fontSize = TextUnit(28f, TextUnitType.Sp)
                    )
                }
            }
        }
    }

    companion object {
        const val COLOR_INTENT_KEY = "Color"
        const val SCREEN_NUM_KEY = "Screen_Num"
    }
}
