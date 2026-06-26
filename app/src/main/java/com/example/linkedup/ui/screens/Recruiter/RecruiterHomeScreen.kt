package com.example.linkedup.ui.screens.Recruiter

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linkedup.data.AuthRepository
import com.example.linkedup.repository.CvTransferRepository

@Composable
fun RecruiterHomeScreen(
    onLogout: () -> Unit
) {

    var sessionId by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Recruiter Dashboard",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                CvTransferRepository.createSession { id ->
                    sessionId = id
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("CV Session starten")
        }

        Spacer(modifier = Modifier.height(12.dp))

        sessionId?.let {
            Text(
                text = "Session Code: $it",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

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