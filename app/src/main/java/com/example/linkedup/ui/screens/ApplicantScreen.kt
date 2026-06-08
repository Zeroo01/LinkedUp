package com.example.linkedup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linkedup.data.AuthRepository

@Composable
fun ApplicantScreen(
    onLogout: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Bewerber Dashboard",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /* CV Upload */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lebenslauf hochladen")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { /* Events */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Events ansehen")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // LogoutButton
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