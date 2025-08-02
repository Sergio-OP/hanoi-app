package com.sosorio.hanoiapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class HanoiScreen {
    @Serializable
    data object Home : HanoiScreen()
}
