package com.sosorio.hanoiapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoot() {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState.value,
    )
}
