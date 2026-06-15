package com.example.linkedup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.linkedup.data.AuthRepository
import com.example.linkedup.repository.EventRepository
import com.example.linkedup.ui.components.ActiveEventCard
import com.example.linkedup.ui.components.EventTimer
import com.example.linkedup.objects.Event
import com.example.linkedup.ui.auth.EventViewModel
import java.util.concurrent.TimeUnit

@Composable
fun EventManagerScreen(
    viewModel: EventViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onLogout: () -> Unit
) {

    val activeEvent = viewModel.activeEvent
    val startTime = remember { System.currentTimeMillis() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text("Event Manager Dashboard")

        Spacer(Modifier.height(16.dp))

        activeEvent?.let { event ->

            val elapsed = System.currentTimeMillis() - startTime
            val minutes = TimeUnit.MILLISECONDS.toMinutes(elapsed)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(elapsed) % 60

            ActiveEventCard(
                event = event,
                onClick = { viewModel.onEventClick(event) },
                timerContent = {
                    EventTimer(minutes, seconds)
                }
            )

            Spacer(Modifier.height(16.dp))
        }

        Button(onClick = viewModel::onCreateEvent) {
            Text("➕ Event erstellen")
        }

        Button(onClick = viewModel::onParticipants) {
            Text("Teilnehmer verwalten")
        }

        Button(onClick = viewModel::onPresentationMode) {
            Text("Präsentationsmodus starten")
        }

        Button(
            onClick = {
                AuthRepository.logout()
                onLogout()
            }
        ) {
            Text("Abmelden")
        }
    }
}