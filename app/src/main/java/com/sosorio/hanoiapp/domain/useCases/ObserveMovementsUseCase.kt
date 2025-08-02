package com.sosorio.hanoiapp.domain.useCases

import com.sosorio.hanoiapp.domain.repository.MovementsRepository

class ObserveMovementsUseCase(
    private val repository: MovementsRepository,
) {
    suspend operator fun invoke(numberOfDisks: Int) = repository.observeMovements(numberOfDisks)
}
