package com.mikeSTF.notesapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val appColorPalette = darkColors(
    primary = BlackShade,
    secondary = White
)

@Composable
fun NotesAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = appColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}