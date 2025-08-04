package com.sosorio.hanoiapp.presentation.screens.home

import com.sosorio.hanoiapp.domain.entities.HanoiTowers
import com.sosorio.hanoiapp.domain.entities.Movement
import com.sosorio.hanoiapp.presentation.components.sheet.AlgorithmConfiguration

data class HomeUiState(
    val configuration: AlgorithmConfiguration =
        AlgorithmConfiguration(
            numberOfDisks = 3,
            movementTimeInMs = 1_000L,
        ),
    val currentMovement: Movement? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val isObserving: Boolean = false,
    val isPaused: Boolean = false,
    val towers: HanoiTowers = emptyList(),
)
