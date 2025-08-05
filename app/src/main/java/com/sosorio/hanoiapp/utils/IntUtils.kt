package com.sosorio.hanoiapp.utils

fun Int.toRodLabel() =
    when (this) {
        0 -> "A"
        1 -> "B"
        2 -> "C"
        else -> ""
    }
