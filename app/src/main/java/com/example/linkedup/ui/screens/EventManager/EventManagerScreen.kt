package com.example.linkedup.ui.screens.eventmanager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

enum class EventManagerTab {
    HOME,
    EVENTS,
    CREATE
}

@Composable
fun EventManagerScreen(
    onLogout: () -> Unit
) {

    var currentTab by remember {
        mutableStateOf(EventManagerTab.HOME)
    }

    Scaffold(

        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = currentTab == EventManagerTab.HOME,
                    onClick = {
                        currentTab = EventManagerTab.HOME
                    },
                    label = {
                        Text("Home")
                    },
                    icon = {}
                )

                NavigationBarItem(
                    selected = currentTab == EventManagerTab.EVENTS,
                    onClick = {
                        currentTab = EventManagerTab.EVENTS
                    },
                    label = {
                        Text("Aktuelle Events")
                    },
                    icon = {}
                )

                NavigationBarItem(
                    selected = currentTab == EventManagerTab.CREATE,
                    onClick = {
                        currentTab = EventManagerTab.CREATE
                    },
                    label = {
                        Text("Neues Event")
                    },
                    icon = {}
                )
            }
        }

    ) { padding ->

        Box(
            modifier = Modifier.padding(padding)
        ) {

            when (currentTab) {

                EventManagerTab.HOME -> {
                    EventManagerHomeScreen(
                        onLogout = onLogout
                    )
                }

                EventManagerTab.EVENTS -> {
                    CurrentEventsScreen()
                }

                EventManagerTab.CREATE -> {
                    CreateEventScreen()
                }
            }
        }
    }
}