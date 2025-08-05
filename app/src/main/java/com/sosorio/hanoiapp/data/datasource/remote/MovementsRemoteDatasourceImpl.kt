package com.sosorio.hanoiapp.data.datasource.remote

import com.sosorio.hanoiapp.domain.datasource.MovementsDatasource
import com.sosorio.hanoiapp.domain.entities.Movement
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.prepareGet
import io.ktor.http.encodedPath
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class MovementsRemoteDatasourceImpl(
    private val httpClient: HttpClient,
) : MovementsDatasource {
    override suspend fun observeMovements(numberOfDisks: Int): Flow<Result<Movement>> =
        flow {
            try {
                httpClient
                    .prepareGet {
                        url {
                            host = HOST
                            encodedPath = "$ENDPOINT/$numberOfDisks"
                        }
                    }.execute { response ->
                        val channel: ByteReadChannel = response.body()
                        while (!channel.isClosedForRead) {
                            val line = channel.readUTF8Line()
                            if (line != null) {
                                val movement = Json.decodeFromString<MovementModel>(line).toMovement()
                                emit(Result.success(movement))
                            }
                        }
                    }
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }

    companion object {
        private const val HOST = "hanoi-service.onrender.com"
        private const val ENDPOINT = "/api/hanoi"
    }
}
