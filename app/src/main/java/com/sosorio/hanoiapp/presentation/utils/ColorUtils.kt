package com.sosorio.hanoiapp.presentation.utils

import androidx.compose.ui.graphics.Color

fun generateRandomColor() =
    Color(
        red = (0..255).random(),
        green = (0..255).random(),
        blue = (0..255).random(),
    )
