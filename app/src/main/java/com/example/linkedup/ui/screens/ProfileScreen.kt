package com.example.linkedup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.linkedup.data.FirebaseTest
import com.example.linkedup.ui.theme.LinkedUpTheme

@Composable
fun ProfileScreen() {

    var firstName by remember { mutableStateOf("Max") }
    var lastName by remember { mutableStateOf("Mustermann") }
    var email by remember { mutableStateOf("max.mustermann@gmail.com") }
    var jobTitle by remember { mutableStateOf("Android Developer") }
    var skills by remember { mutableStateOf("Kotlin, Jetpack Compose, Firebase") }
    var interests by remember { mutableStateOf("IT, Startups, Mobile Apps") }

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
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {

                Column(modifier = Modifier.padding(20.dp)) {

                    Text(
                        text = "Persönliche Daten",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = { Text("Vorname") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Nachname") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("E-Mail") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = jobTitle,
                        onValueChange = { jobTitle = it },
                        label = { Text("Beruf") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {

                Column(modifier = Modifier.padding(20.dp)) {

                    Text(
                        text = "Skills",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = skills,
                        onValueChange = { skills = it },
                        label = { Text("Kenntnisse") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Interessen",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = interests,
                        onValueChange = { interests = it },
                        label = { Text("Unternehmen / Branchen") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {

            Button(
                onClick = {
                    // TODO: Save Profile später (Firebase / Local DB)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Profil speichern")
            }

            Button(
                onClick = {
                    FirebaseTest.writeTestUser()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Firebase Test starten")
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = {
                    // TODO: CV Upload später
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Lebenslauf hochladen")
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    LinkedUpTheme {
        ProfileScreen()
    }
}