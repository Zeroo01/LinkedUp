/**package com.example.linkedup.ui.auth

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linkedup.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repo: AuthRepository
) : ViewModel() {

    var loading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            loading = true
            error = null

            val result = repo.login(email, password)

            loading = false

            result.onSuccess {
                onSuccess()
            }.onFailure {
                error = it.message
            }
        }
    }

    fun register(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            loading = true
            error = null

            val result = repo.register(email, password)

            loading = false

            result.onSuccess {
                onSuccess()
            }.onFailure {
                error = it.message
            }
        }
    }
}**/