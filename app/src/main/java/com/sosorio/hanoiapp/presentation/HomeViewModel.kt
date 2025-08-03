package com.sosorio.hanoiapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sosorio.hanoiapp.domain.useCases.ObserveMovementsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val observeMovementsUseCase: ObserveMovementsUseCase,
    private val dispatchers: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeMovements(10)
    }

    private fun observeMovements(numberOfDisks: Int) =
        viewModelScope.launch(dispatchers) {
            observeMovementsUseCase(numberOfDisks).collect { movementResult ->
                movementResult
                    .onSuccess { movement ->
                        _uiState.update { it.copy(movement = movement) }
                        delay(200L)
                    }.onFailure { error ->
                        _uiState.update { it.copy(error = error.message) }
                        this.cancel()
                    }
            }
        }
}
