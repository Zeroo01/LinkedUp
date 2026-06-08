package com.example.linkedup.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linkedup.data.AuthRepository

@Composable
fun AuthScreen(
    onLoginSuccess: (String) -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isLoginMode by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

    var selectedRole by remember { mutableStateOf("APPLICANT") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = if (isLoginMode) "Login" else "Registrieren",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        // EMAIL
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-Mail") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // PASSWORD
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Passwort") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Rollen Auswahl (nur im Register-Modus)
        if (!isLoginMode) {

            Text(
                text = "Rolle",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                RadioButton(
                    selected = selectedRole == "APPLICANT",
                    onClick = { selectedRole = "APPLICANT" }
                )
                Text("Bewerber")
            }

            Row {
                RadioButton(
                    selected = selectedRole == "RECRUITER",
                    onClick = { selectedRole = "RECRUITER" }
                )
                Text("Recruiter")
            }

            Row {
                RadioButton(
                    selected = selectedRole == "EVENT_MANAGER",
                    onClick = { selectedRole = "EVENT_MANAGER" }
                )
                Text("Event Manager")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Login / Registrierungs-Button
        Button(
            onClick = {

                loading = true
                errorMessage = null

                if (isLoginMode) {

                    AuthRepository.login(email, password) { success, error, role ->

                        loading = false

                        if (success) {
                            onLoginSuccess(role ?: "APPLICANT")
                        } else {
                            errorMessage = error
                        }
                    }

                } else {

                    AuthRepository.register(
                        email,
                        password,
                        selectedRole
                    ) { success, error, role ->

                        loading = false

                        if (success) {
                            onLoginSuccess(role ?: selectedRole)
                        } else {
                            errorMessage = error
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = email.isNotBlank() && password.isNotBlank() && !loading
        ) {
            Text(
                text =
                    if (loading) "Lädt..."
                    else if (isLoginMode) "Login"
                    else "Registrieren"
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Switch
        TextButton(
            onClick = {
                isLoginMode = !isLoginMode
                errorMessage = null
            }
        ) {
            Text(
                if (isLoginMode)
                    "Noch kein Konto? Registrieren"
                else
                    "Schon ein Konto? Login"
            )
        }

        // Fehlermeldung
        errorMessage?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}