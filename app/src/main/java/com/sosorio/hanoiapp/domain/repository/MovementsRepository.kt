package com.sosorio.hanoiapp.domain.repository

import com.sosorio.hanoiapp.domain.entities.Movement
import kotlinx.coroutines.flow.Flow

interface MovementsRepository {
    suspend fun observeMovements(numberOfDisks: Int): Flow<Movement>
}
