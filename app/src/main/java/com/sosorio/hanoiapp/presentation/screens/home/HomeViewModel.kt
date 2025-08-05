package com.sosorio.hanoiapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sosorio.hanoiapp.domain.entities.HanoiGame
import com.sosorio.hanoiapp.domain.useCases.ObserveMovementsUseCase
import com.sosorio.hanoiapp.presentation.components.label.MovementLog
import com.sosorio.hanoiapp.presentation.components.sheet.AlgorithmConfiguration
import com.sosorio.hanoiapp.utils.toRodLabel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
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
        createGame()
        updateUiTowers()
    }

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.ConfigureAlgorithm -> configureAlgorithm(intent.configuration)
            HomeIntent.StartAlgorithm -> startAlgorithm()
            HomeIntent.RestartAlgorithm -> restartAlgorithm()
            HomeIntent.PauseAlgorithm -> pauseAlgorithm()
            HomeIntent.ResumeAlgorithm -> resumeAlgorithm()
            HomeIntent.NextStep -> TODO()
        }
    }

    private fun configureAlgorithm(configuration: AlgorithmConfiguration) {
        if (configuration.isValid) {
            updateConfiguration(configuration)
            restartAlgorithm()
        }
    }

    private fun startAlgorithm() {
        restartAlgorithm()
        observeMovements()
    }

    private fun restartAlgorithm() {
        cancelObservation()
        createGame()
        updateUiTowers()
        clearMovementHistory()
    }

    private fun pauseAlgorithm() = _uiState.update { it.copy(isPaused = true) }

    private fun resumeAlgorithm() = _uiState.update { it.copy(isPaused = false) }

    private fun updateConfiguration(configuration: AlgorithmConfiguration) = _uiState.update { it.copy(configuration = configuration) }

    private fun cancelObservation() {
        observeJob?.cancel()
        observeJob = null
        setObservingStatus(false)
        setLoadingStatus(false)
    }

    private fun updateUiTowers() =
        _uiState.update { state ->
            state.copy(towers = game.towers.map { ArrayDeque(it) })
        }

    private fun createGame() {
        game = HanoiGame(numberOfDisks = _uiState.value.configuration.numberOfDisks)
    }

    private fun observeMovements() {
        observeJob =
            viewModelScope.launch(dispatchers) {
                setLoadingStatus(true)
                val numberOfDisks = _uiState.value.configuration.numberOfDisks

                observeMovementsUseCase(numberOfDisks)
                    .collect { movementResult ->
                        setLoadingStatus(false)
                        movementResult
                            .onSuccess { movement ->
                                setObservingStatus(true)
                                moveDisk(movement.start - 1, movement.end - 1)
                                updateUiTowers()
                                waitDelay()
                            }.onFailure { error ->
                                updateErrorMessage(error.message)
                                cancelObservation()
                            }
                    }
            }
    }

    private fun moveDisk(
        from: Int,
        to: Int,
    ) {
        registerMovementLog(from, to)
        game.moveDisk(from, to)
    }

    private fun registerMovementLog(
        from: Int,
        to: Int,
    ) {
        val diskId = game.towers[from].first().index
        val movementLog =
            MovementLog(
                diskIndex = diskId,
                fromRod = from.toRodLabel(),
                toRod = to.toRodLabel(),
            )
        _uiState.update { it.copy(movementHistory = it.movementHistory + movementLog) }
    }

    private fun setLoadingStatus(isLoading: Boolean) = _uiState.update { it.copy(isLoading = isLoading) }

    private fun setObservingStatus(isObserving: Boolean) = _uiState.update { it.copy(isObserving = isObserving) }

    private fun updateErrorMessage(message: String?) = _uiState.update { it.copy(errorMessage = message) }

    private fun clearMovementHistory() = _uiState.update { it.copy(movementHistory = emptyList()) }

    private suspend fun waitDelay() = delay(_uiState.value.configuration.movementTimeInMs)
}
