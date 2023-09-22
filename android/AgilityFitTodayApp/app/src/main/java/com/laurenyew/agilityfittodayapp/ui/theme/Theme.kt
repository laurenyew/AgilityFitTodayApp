package com.laurenyew.agilityfittodayapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = gold200,
    primaryVariant = gold700,
    secondary = teal200
)

private val LightColorPalette = lightColors(
    primary = gold500,
    primaryVariant = gold200,
    secondary = teal200,
    surface = gold100
)

@Composable
fun AgilityFitTodayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
