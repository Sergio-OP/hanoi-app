package com.sosorio.hanoiapp.di

import com.sosorio.hanoiapp.data.datasource.remote.MovementsRemoteDatasourceImpl
import com.sosorio.hanoiapp.data.repository.MovementsRepositoryImpl
import com.sosorio.hanoiapp.domain.datasource.MovementsDatasource
import com.sosorio.hanoiapp.domain.repository.MovementsRepository
import com.sosorio.hanoiapp.domain.useCases.ObserveMovementsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule =
    module {
        singleOf(::MovementsRemoteDatasourceImpl) bind MovementsDatasource::class
        singleOf(::MovementsRepositoryImpl) bind MovementsRepository::class
        singleOf(::ObserveMovementsUseCase)
    }
