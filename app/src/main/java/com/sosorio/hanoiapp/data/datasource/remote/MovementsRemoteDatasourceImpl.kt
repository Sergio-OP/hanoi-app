package com.sosorio.hanoiapp.data.datasource.remote

import com.sosorio.hanoiapp.domain.datasource.MovementsDatasource
import com.sosorio.hanoiapp.domain.entities.Movement
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.encodedPath
import io.ktor.utils.io.jvm.javaio.toInputStream
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import java.io.IOException

class MovementsRemoteDatasourceImpl(
    private val httpClient: HttpClient,
) : MovementsDatasource {
    override suspend fun observeMovements(numberOfDisks: Int): Flow<Result<Movement>> =
        flow {
            try {
                val response = fetchMovements(numberOfDisks)
                validateResponse(response)

                val reader = response.bodyAsChannel().toInputStream().bufferedReader()
                reader.useLines { lines ->
                    lines.forEach { line ->
                        val movement = Json.decodeFromString<MovementModel>(line).toMovement()
                        emit(Result.success(movement))
                    }
                }
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }

    private suspend fun fetchMovements(numberOfDisks: Int): HttpResponse =
        httpClient.get {
            url {
                host = HOST
                encodedPath = "$ENDPOINT/$numberOfDisks"
            }
        }

    private fun validateResponse(response: HttpResponse) {
        if (response.status.value !in 200..299) {
            throw IOException(response.status.description)
        }
    }

    companion object {
        private const val HOST = "192.168.1.194:8080"
        private const val ENDPOINT = "/api/hanoi"
    }
}
