package com.example.linkedup.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linkedup.data.AuthRepository

@Composable
fun AuthScreen(
    onLoginSuccess: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isLoginMode by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

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

        // LOGIN / REGISTER BUTTON
        Button(
            onClick = {

                loading = true
                errorMessage = null

                if (isLoginMode) {

                    AuthRepository.login(email, password) { success, error ->

                        loading = false

                        if (success) {
                            onLoginSuccess()
                        } else {
                            errorMessage = error
                        }
                    }

                } else {

                    AuthRepository.register(email, password) { success, error ->

                        loading = false

                        if (success) {
                            onLoginSuccess()
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
                if (loading)
                    "Lädt..."
                else if (isLoginMode)
                    "Login"
                else
                    "Registrieren"
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // MODE SWITCH
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

        // ERROR
        errorMessage?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}