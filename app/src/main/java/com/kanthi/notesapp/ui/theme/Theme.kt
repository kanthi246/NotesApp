package com.kanthi.notesapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val NotesColorScheme = lightColorScheme(
    primary = AccentLight,
    onPrimary = Color.White,
    secondary = Accent2Light,
    onSecondary = Color.White,
    background = BackgroundLight,
    onBackground = TextLight,
    surface = SurfaceLight,
    onSurface = TextLight,
    outline = DividerLight,
)

@Composable
fun NotesAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = NotesColorScheme,
        typography = Typography,
        content = content
    )
}
