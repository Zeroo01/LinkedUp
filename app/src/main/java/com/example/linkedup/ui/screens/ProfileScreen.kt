package com.example.linkedup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.linkedup.data.AuthRepository
import com.example.linkedup.data.ProfileRepository
import com.example.linkedup.ui.components.ProfileCard
import com.example.linkedup.ui.components.ProfileRow

@Composable
fun ProfileScreen(
    onLogout: () -> Unit
) {

    val email = AuthRepository.getCurrentEmail()

    var isEditing by remember { mutableStateOf(false) }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var jobTitle by remember { mutableStateOf("") }
    var skills by remember { mutableStateOf("") }
    var interests by remember { mutableStateOf("") }

    LaunchedEffect(email) {
        email?.let {
            ProfileRepository.loadProfile(it) { data ->
                firstName = data["firstName"] as? String ?: ""
                lastName = data["lastName"] as? String ?: ""
                jobTitle = data["jobTitle"] as? String ?: ""
                skills = data["skills"] as? String ?: ""
                interests = data["interests"] as? String ?: ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Profil",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = { isEditing = !isEditing }
            ) {
                Text(if (isEditing) "Abbrechen" else "Bearbeiten")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ProfileCard(title = "Persönliche Daten") {

            ProfileRow(
                label = "Vorname",
                value = firstName,
                isEditing = isEditing,
                onValueChange = { firstName = it }
            )

            ProfileRow(
                label = "Nachname",
                value = lastName,
                isEditing = isEditing,
                onValueChange = { lastName = it }
            )

            ProfileRow(
                label = "Beruf",
                value = jobTitle,
                isEditing = isEditing,
                onValueChange = { jobTitle = it }
            )
        }

        ProfileCard(title = "Skills") {

            ProfileRow(
                label = "Kenntnisse",
                value = skills,
                isEditing = isEditing,
                onValueChange = { skills = it }
            )

            ProfileRow(
                label = "Interessen",
                value = interests,
                isEditing = isEditing,
                onValueChange = { interests = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (isEditing) {

            Button(
                onClick = {
                    email?.let {
                        ProfileRepository.updateProfile(
                            email = it,
                            firstName = firstName,
                            lastName = lastName,
                            jobTitle = jobTitle,
                            skills = skills,
                            interests = interests
                        )
                    }
                    isEditing = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Fertig")
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Abmelden")
        }
    }
}