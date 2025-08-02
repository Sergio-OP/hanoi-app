package com.sosorio.hanoiapp.domain.datasource

import com.sosorio.hanoiapp.domain.entities.Movement
import kotlinx.coroutines.flow.Flow

interface MovementsDatasource {
    fun observeMovements(): Flow<List<Movement>>
}