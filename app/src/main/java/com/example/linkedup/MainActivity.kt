package com.example.linkedup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.example.linkedup.ui.screens.*
import com.example.linkedup.ui.theme.LinkedUpTheme
import com.example.linkedup.objects.Event

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

    var currentScreen by remember { mutableStateOf(Screen.HOME) }
    var selectedEvent by remember { mutableStateOf<Event?>(null) }

    Scaffold(
        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = currentScreen == Screen.HOME,
                    onClick = {
                        currentScreen = Screen.HOME
                        selectedEvent = null
                    },
                    label = { Text("Home") },
                    icon = {}
                )

                NavigationBarItem(
                    selected = currentScreen == Screen.MATCHES,
                    onClick = {
                        currentScreen = Screen.MATCHES
                        selectedEvent = null
                    },
                    label = { Text("Matches") },
                    icon = {}
                )

                NavigationBarItem(
                    selected = currentScreen == Screen.PROFILE,
                    onClick = {
                        currentScreen = Screen.PROFILE
                        selectedEvent = null
                    },
                    label = { Text("Profil") },
                    icon = {}
                )
            }
        }
    ) { padding ->

        Box(modifier = Modifier.padding(padding)) {

            when {

                // EVENT DETAIL (HIGHEST PRIORITY)
                selectedEvent != null -> {

                    EventDetailScreen(
                        event = selectedEvent!!,
                        onBack = { selectedEvent = null }
                    )
                }

                currentScreen == Screen.HOME -> {

                    HomeScreen(
                        onEventClick = { event ->
                            selectedEvent = event
                        }
                    )
                }

                currentScreen == Screen.MATCHES -> MatchesScreen()

                currentScreen == Screen.PROFILE -> ProfileScreen()
            }
        }
    }
}