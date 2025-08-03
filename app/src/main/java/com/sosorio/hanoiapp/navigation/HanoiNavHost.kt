package com.sosorio.hanoiapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sosorio.hanoiapp.presentation.HomeRoot

@Composable
fun HanoiNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HanoiScreen.Home,
        modifier = modifier,
    ) {
        composable<HanoiScreen.Home> {
            HomeRoot()
        }
    }
}
