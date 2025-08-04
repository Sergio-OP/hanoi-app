package com.sosorio.hanoiapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sosorio.hanoiapp.domain.entities.HanoiGame
import com.sosorio.hanoiapp.domain.useCases.ObserveMovementsUseCase
import com.sosorio.hanoiapp.presentation.components.sheet.AlgorithmConfiguration
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val observeMovementsUseCase: ObserveMovementsUseCase,
    private val dispatchers: CoroutineDispatcher,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    private lateinit var game: HanoiGame
    private var observeJob: Job? = null

    init {
        startGame()
    }

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.StartGame -> observeMovements(_uiState.value.configuration.numberOfDisks)
            HomeIntent.RefreshGame -> startGame()
            is HomeIntent.ConfigureAlgorithm -> configureAlgorithm(intent.configuration)
        }
    }

    private fun configureAlgorithm(configuration: AlgorithmConfiguration) {
        if (configuration.numberOfDisks > 0 && configuration.movementTimeInMs > 0) {
            _uiState.update { it.copy(configuration = configuration) }
            startGame()
        }
    }

    private fun startGame() {
        game = HanoiGame(numberOfDisks = _uiState.value.configuration.numberOfDisks)
        cancelObservation()
        updateUiTowers()
    }

    private fun moveDisk(
        from: Int,
        to: Int,
    ) {
        game.moveDisk(from, to)
        updateUiTowers()
    }

    private fun updateUiTowers() = _uiState.update { it.copy(towers = game.towers.map { ArrayDeque(it) }) }

    private fun observeMovements(numberOfDisks: Int) {
        startGame()

        observeJob =
            viewModelScope.launch(dispatchers) {
                observeMovementsUseCase(numberOfDisks).collect { movementResult ->
                    movementResult
                        .onSuccess { movement ->
                            _uiState.update { it.copy(lastMovement = movement) }
                            moveDisk(movement.start - 1, movement.end - 1)
                            delay(_uiState.value.configuration.movementTimeInMs)
                        }.onFailure { error ->
                            _uiState.update { it.copy(error = error.message) }
                            this.cancel()
                        }
                }
            }
    }

    private fun cancelObservation() {
        observeJob?.cancel()
        observeJob = null
    }
}
