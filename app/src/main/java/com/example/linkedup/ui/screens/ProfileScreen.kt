package com.example.linkedup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.linkedup.data.AuthRepository
import com.example.linkedup.data.FirebaseProfileRepository

@Composable
fun ProfileScreen(
    onLogout: () -> Unit = {}
) {

    var firstName by remember { mutableStateOf("Max") }
    var lastName by remember { mutableStateOf("Mustermann") }
    var email by remember { mutableStateOf("max@test.com") }
    var jobTitle by remember { mutableStateOf("Android Developer") }
    var skills by remember { mutableStateOf("Kotlin, Compose") }
    var interests by remember { mutableStateOf("IT, Startups") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {

            Text(
                text = "Profil",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {

                Column(modifier = Modifier.padding(20.dp)) {

                    Text(
                        text = "Persönliche Daten",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(12.dp))

                    Text("Vorname: $firstName")
                    Text("Nachname: $lastName")
                    Text("E-Mail: $email")
                    Text("Beruf: $jobTitle")
                }
            }

            Spacer(Modifier.height(20.dp))
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {

                Column(modifier = Modifier.padding(20.dp)) {

                    Text(
                        text = "Skills",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))
                    Text(skills)

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = "Interessen",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))
                    Text(interests)
                }
            }

            Spacer(Modifier.height(30.dp))
        }

        item {

            // 🔴 LOGOUT BUTTON
            Button(
                onClick = {
                    AuthRepository.logout()
                    onLogout()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Abmelden")
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}