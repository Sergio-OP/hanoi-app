package com.sosorio.hanoiapp.presentation.screens.home

sealed class HomeIntent {
    data object StartGame : HomeIntent()

    data object RefreshGame : HomeIntent()

    data class ConfigureAlgorithm(
        val numberOfDisks: Int,
        val moveAnimationTimeMs: Long,
    ) : HomeIntent()
}
