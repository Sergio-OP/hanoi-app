package com.sosorio.hanoiapp.di

import com.sosorio.hanoiapp.network.createHttpClient
import com.sosorio.hanoiapp.network.createHttpClientEngine
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.koin.dsl.module

val networkModule =
    module {
        single<HttpClientEngine> { createHttpClientEngine() }
        single<HttpClient> { createHttpClient(get()) }
    }
