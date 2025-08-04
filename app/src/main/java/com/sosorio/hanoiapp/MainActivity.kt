package com.sosorio.hanoiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.ui.Modifier
import com.sosorio.hanoiapp.navigation.HanoiNavHost
import com.sosorio.hanoiapp.ui.theme.HanoiAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HanoiAppTheme {
                HanoiNavHost(
                    modifier = Modifier.navigationBarsPadding(),
                )
            }
        }
    }
}
