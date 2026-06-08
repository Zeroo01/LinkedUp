/**package com.example.linkedup.ui.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.linkedup.data.ProfileRepository
import com.example.linkedup.objects.Profile

class ProfileViewModel(
    private val repo: ProfileRepository = ProfileRepository()
) : ViewModel() {

    var profile by mutableStateOf(Profile())
        private set

    fun load(email: Unit) {
        repo.loadProfile(email) {
            profile = it
        }
    }

    fun update(email: Unit) {
        repo.updateProfile(email, profile)
    }

    fun setFirstName(value: String) {
        profile = profile.copy(firstName = value)
    }

    fun setLastName(value: String) {
        profile = profile.copy(lastName = value)
    }

    fun setJobTitle(value: String) {
        profile = profile.copy(jobTitle = value)
    }

    fun setSkills(value: String) {
        profile = profile.copy(skills = value)
    }

    fun setInterests(value: String) {
        profile = profile.copy(interests = value)
    }
}**/