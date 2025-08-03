package com.sosorio.hanoiapp.presentation

import com.sosorio.hanoiapp.domain.entities.Movement

data class HomeUiState(
    val movement: Movement? = null,
    val error: String? = null,
)
