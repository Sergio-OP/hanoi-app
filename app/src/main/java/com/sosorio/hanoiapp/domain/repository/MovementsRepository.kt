package com.sosorio.hanoiapp.domain.repository

import com.sosorio.hanoiapp.domain.entities.Movement
import kotlinx.coroutines.flow.Flow

interface MovementsRepository {
    fun observeMovements(): Flow<List<Movement>>
}
