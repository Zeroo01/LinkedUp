package com.example.linkedup.ui.screens.Applicant

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linkedup.repository.CvTransferRepository
import com.example.linkedup.data.AuthRepository

@Composable
fun CvSendScreen() {

    var sessionId by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "CV senden",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = sessionId,
            onValueChange = { sessionId = it },
            label = { Text("Session Code") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                val userId = AuthRepository.getCurrentEmail() ?: "unknown"

                CvTransferRepository.sendCv(
                    sessionId = sessionId,
                    userId = userId,
                    cvUrl = "dummy_cv_url"
                )

                status = "CV gesendet"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("CV senden")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = status)
    }
}