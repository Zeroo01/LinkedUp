package com.example.linkedup.model

// Konstruktor mit bestimmter Rollenangabe
data class UserData(
    val uid: String = "",
    val email: String = "",
    val role: UserRole = UserRole.APPLICANT
)