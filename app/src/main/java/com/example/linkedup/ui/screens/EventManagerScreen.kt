package com.example.linkedup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linkedup.data.AuthRepository

@Composable
fun EventManagerScreen(
    onLogout: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Event Manager Dashboard",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /* Event erstellen */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("➕ Event erstellen")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { /* Teilnehmer */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Teilnehmer verwalten")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { /* Präsentationsmodus */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Präsentationsmodus starten")
        }

        Button(
            onClick = {
                AuthRepository.logout()
                onLogout()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Abmelden")
        }
    }
}