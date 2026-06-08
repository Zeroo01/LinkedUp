/**package com.example.linkedup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.linkedup.data.repository.AuthRepository
import com.example.linkedup.ui.auth.ProfileViewModel

@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    viewModel: ProfileViewModel = remember { ProfileViewModel() }
) {

    val email = AuthRepository.getCurrentEmail()

    val profile = viewModel.profile
    var isEditing by remember { mutableStateOf(false) }

    LaunchedEffect(email) {
        email.let { viewModel.load(it) }
    }

    Column {

        Text("Profil")

        if (isEditing) {

            OutlinedTextField(
                value = profile.firstName,
                onValueChange = viewModel::setFirstName
            )

            OutlinedTextField(
                value = profile.lastName,
                onValueChange = viewModel::setLastName
            )

            OutlinedTextField(
                value = profile.jobTitle,
                onValueChange = viewModel::setJobTitle
            )
        }

        Button(onClick = {
            email.let { viewModel.update(it) }
            isEditing = false
        }) {
            Text("Speichern")
        }

        Button(onClick = onLogout) {
            Text("Logout")
        }
    }
}**/