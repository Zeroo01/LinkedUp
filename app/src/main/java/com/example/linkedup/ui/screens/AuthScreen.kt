package com.example.linkedup.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linkedup.data.AuthRepository

@Composable
fun AuthScreen(
    onLoginSuccess: (String) -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isLoginMode by remember { mutableStateOf(true) }
    var selectedRole by remember { mutableStateOf("APPLICANT") }

    var loading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Bereits eingeloggt?
    LaunchedEffect(Unit) {

        if (AuthRepository.isLoggedIn()) {

            AuthRepository.getUserRole { role ->

                role?.let {
                    onLoginSuccess(it)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(24.dp)
        ) {

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "LinkedUp",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Recruiting für Karrieremessen",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = if (isLoginMode) "Anmelden" else "Registrieren",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-Mail") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Passwort") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )

                if (!isLoginMode) {

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Rolle auswählen",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        RoleOption(
                            text = "Bewerber",
                            selected = selectedRole == "APPLICANT"
                        ) {
                            selectedRole = "APPLICANT"
                        }

                        RoleOption(
                            text = "Recruiter",
                            selected = selectedRole == "RECRUITER"
                        ) {
                            selectedRole = "RECRUITER"
                        }

                        RoleOption(
                            text = "Event Manager",
                            selected = selectedRole == "EVENT_MANAGER"
                        ) {
                            selectedRole = "EVENT_MANAGER"
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {

                        loading = true
                        errorMessage = null

                        if (isLoginMode) {

                            AuthRepository.login(
                                email,
                                password
                            ) { success, error, role ->

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    enabled = !loading &&
                            email.isNotBlank() &&
                            password.isNotBlank()
                ) {

                    Text(
                        if (loading)
                            "Bitte warten..."
                        else if (isLoginMode)
                            "Anmelden"
                        else
                            "Registrieren"
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

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
                            "Bereits registriert? Anmelden"
                    )
                }

                errorMessage?.let {

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
private fun RoleOption(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(
            selected = selected,
            onClick = onSelect
        )

        Text(text)
    }
}