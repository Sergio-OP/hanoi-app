package com.sosorio.hanoiapp.domain.useCases

import com.sosorio.hanoiapp.domain.repository.MovementsRepository

class ObserveMovementsUseCase(
    private val repository: MovementsRepository
) {
    operator fun invoke() = repository.observeMovements()
}