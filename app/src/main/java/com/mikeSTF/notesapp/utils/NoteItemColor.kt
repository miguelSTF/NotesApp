package com.mikeSTF.notesapp.utils

import androidx.compose.ui.graphics.Color
import com.mikeSTF.notesapp.ui.theme.*

fun noteItemColor(index: Int): Color {
    val listOfColors = listOf(
        RichBrilliantLavender, LightSalmonPink, LightGreen, PastelYellow, Waterspout, PaleViolet
    )

    val colorIndex = index % listOfColors.size

    return listOfColors[colorIndex]
}