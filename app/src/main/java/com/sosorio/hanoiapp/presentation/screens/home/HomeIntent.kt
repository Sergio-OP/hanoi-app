package com.sosorio.hanoiapp.presentation.screens.home

import com.sosorio.hanoiapp.presentation.components.sheet.AlgorithmConfiguration

sealed class HomeIntent {
    data object StartGame : HomeIntent()

    data object RefreshGame : HomeIntent()

    data class ConfigureAlgorithm(
        val configuration: AlgorithmConfiguration,
    ) : HomeIntent()
}
