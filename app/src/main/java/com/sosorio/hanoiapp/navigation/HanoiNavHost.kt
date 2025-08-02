package com.sosorio.hanoiapp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun HanoiNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HanoiScreen.Home,
        modifier = modifier,
    ) {
        composable<HanoiScreen.Home> {
            Text(text = "Home", color = Color.White)
        }
    }
}
