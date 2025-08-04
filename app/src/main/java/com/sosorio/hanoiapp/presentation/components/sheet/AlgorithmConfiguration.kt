package com.sosorio.hanoiapp.presentation.components.sheet

data class AlgorithmConfiguration(
    val numberOfDisks: Int,
    val movementTimeInMs: Long,
) {
    val isValid: Boolean
        get() = numberOfDisks > 0 && movementTimeInMs > 0
}
