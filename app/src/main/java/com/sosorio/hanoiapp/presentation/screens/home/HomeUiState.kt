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
    val lastMovement: Movement? = null,
    val error: String? = null,
    val towers: HanoiTowers = emptyList(),
)
