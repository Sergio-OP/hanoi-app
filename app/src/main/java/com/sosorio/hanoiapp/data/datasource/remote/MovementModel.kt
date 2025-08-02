package com.sosorio.hanoiapp.data.datasource.remote

import com.sosorio.hanoiapp.domain.entities.Movement
import kotlinx.serialization.Serializable

@Serializable
data class MovementModel(
    val start: Int,
    val end: Int,
)

fun MovementModel.toMovement() =
    Movement(
        start = this.start,
        end = this.end,
    )
