package com.sosorio.hanoiapp.presentation.screens.home

import com.sosorio.hanoiapp.presentation.components.sheet.AlgorithmConfiguration

sealed class HomeIntent {
    data object StartAlgorithm : HomeIntent()

    data object RestartAlgorithm : HomeIntent()

    data class MoveDisk(
        val from: Int,
        val to: Int,
    ) : HomeIntent()

    data class ConfigureAlgorithm(
        val configuration: AlgorithmConfiguration,
    ) : HomeIntent()
}
