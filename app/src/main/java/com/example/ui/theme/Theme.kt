package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val LiquidGlassColorScheme = darkColorScheme(
    primary = DeepCyan,
    onPrimary = PitchBlack,
    background = PitchBlack,
    onBackground = TypographyPrimary,
    surface = GlassDarkGrey,
    onSurface = TypographyPrimary,
    error = ErrorRed,
    onError = PitchBlack
)

@Composable
fun MyApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LiquidGlassColorScheme,
        typography = Typography,
        content = content
    )
}
