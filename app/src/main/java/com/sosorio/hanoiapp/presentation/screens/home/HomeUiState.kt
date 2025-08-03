package com.sosorio.hanoiapp.presentation.screens.home

import com.sosorio.hanoiapp.domain.entities.HanoiTowers
import com.sosorio.hanoiapp.domain.entities.Movement

data class HomeUiState(
    val numberOfDisks: Int = 3,
    val moveAnimationTimeMs: Long = 1_000L,
    val lastMovement: Movement? = null,
    val error: String? = null,
    val towers: HanoiTowers = emptyList(),
)
