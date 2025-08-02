package com.sosorio.hanoiapp.data.repository

import com.sosorio.hanoiapp.domain.datasource.MovementsDatasource
import com.sosorio.hanoiapp.domain.repository.MovementsRepository

class MovementsRepositoryImpl(
    private val datasource: MovementsDatasource,
) : MovementsRepository {
    override suspend fun observeMovements(numberOfDisks: Int) = datasource.observeMovements(numberOfDisks)
}
