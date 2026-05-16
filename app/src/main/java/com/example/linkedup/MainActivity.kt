package com.example.linkedup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.example.linkedup.ui.screens.HomeScreen
import com.example.linkedup.ui.screens.MatchesScreen
import com.example.linkedup.ui.screens.ProfileScreen
import com.example.linkedup.ui.theme.LinkedUpTheme

enum class Screen {
    HOME,
    MATCHES,
    PROFILE
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LinkedUpTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {

    var currentScreen by remember {
        mutableStateOf(Screen.HOME)
    }

    Scaffold(
        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = currentScreen == Screen.HOME,
                    onClick = { currentScreen = Screen.HOME },
                    label = { Text("Home") },
                    icon = {}
                )

                NavigationBarItem(
                    selected = currentScreen == Screen.MATCHES,
                    onClick = { currentScreen = Screen.MATCHES },
                    label = { Text("Matches") },
                    icon = {}
                )

                NavigationBarItem(
                    selected = currentScreen == Screen.PROFILE,
                    onClick = { currentScreen = Screen.PROFILE },
                    label = { Text("Profil") },
                    icon = {}
                )
            }
        }
    ) { padding ->

        Box(modifier = Modifier.padding(padding)) {

            when (currentScreen) {

                Screen.HOME -> HomeScreen(
                    onEventClick = {}
                )

                Screen.MATCHES -> MatchesScreen()

                Screen.PROFILE -> ProfileScreen()
            }
        }
    }
}