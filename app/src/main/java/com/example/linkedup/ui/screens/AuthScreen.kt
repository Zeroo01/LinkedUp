package com.example.linkedup.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthScreen(
    onLoginSuccess: () -> Unit
) {

    val viewModel = remember { AuthViewModel() }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isLoginMode by remember { mutableStateOf(true) }

    val loading = viewModel.loading
    val errorMessage = viewModel.error

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

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-Mail") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Passwort") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                if (isLoginMode) {
                    viewModel.login(email, password, onLoginSuccess)
                } else {
                    viewModel.register(email, password, onLoginSuccess)
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

        Spacer(Modifier.height(12.dp))

        TextButton(
            onClick = {
                isLoginMode = !isLoginMode
            }
        ) {
            Text(
                if (isLoginMode)
                    "Noch kein Konto? Registrieren"
                else
                    "Schon ein Konto? Login"
            )
        }

        errorMessage?.let {
            Spacer(Modifier.height(12.dp))
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}