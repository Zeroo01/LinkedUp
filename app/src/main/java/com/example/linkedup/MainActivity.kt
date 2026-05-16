package com.example.linkedup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.example.linkedup.data.AuthRepository
import com.example.linkedup.ui.auth.AuthScreen
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
                AppRoot()
            }
        }
    }
}

@Composable
fun AppRoot() {

    var isLoggedIn by remember {
        mutableStateOf(AuthRepository.isLoggedIn())
    }

    if (!isLoggedIn) {

        // 🔐 LOGIN / REGISTER SCREEN
        AuthScreen(
            onLoginSuccess = {
                isLoggedIn = true
            }
        )

    } else {

        // 📱 APP START
        App(
            onLogout = {
                isLoggedIn = false
            }
        )
    }
}

@Composable
fun App(
    onLogout: () -> Unit
) {

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

            // 🔥 EVENT DETAIL PRIORITY
            if (selectedEvent != null) {

                EventDetailScreen(
                    event = selectedEvent!!,
                    onBack = { selectedEvent = null }
                )

            } else {

                when (currentScreen) {

                    Screen.HOME -> HomeScreen(
                        onEventClick = { event ->
                            selectedEvent = event
                        }
                    )

                    Screen.MATCHES -> MatchesScreen()

                    Screen.PROFILE -> ProfileScreen(
                        onLogout = {
                            AuthRepository.logout()
                            onLogout()
                        }
                    )
                }
            }
        }
    }
}