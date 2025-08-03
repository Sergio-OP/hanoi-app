package com.sosorio.hanoiapp.utils

fun generateRandomLongColor(): Long {
    val alpha = 0xFFL shl 24
    val red = (0..255).random() shl 16
    val green = (0..255).random() shl 8
    val blue = (0..255).random()
    return alpha or red.toLong() or green.toLong() or blue.toLong()
}
