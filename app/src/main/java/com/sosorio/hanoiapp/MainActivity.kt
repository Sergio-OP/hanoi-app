package com.sosorio.hanoiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sosorio.hanoiapp.navigation.HanoiNavHost
import com.sosorio.hanoiapp.ui.theme.HanoiAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HanoiAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HanoiNavHost(Modifier.padding(innerPadding))
                }
            }
        }
    }
}
