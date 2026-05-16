package com.example.linkedup.data

import com.google.firebase.auth.FirebaseAuth

object AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // 🔐 LOGIN
    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult(true, null)
            }
            .addOnFailureListener { error ->
                onResult(false, error.message)
            }
    }

    // 🆕 REGISTER
    fun register(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult(true, null)
            }
            .addOnFailureListener { error ->
                onResult(false, error.message)
            }
    }

    // 👤 CHECK LOGIN STATUS
    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    // 📧 CURRENT USER EMAIL
    fun getCurrentEmail(): String? {
        return auth.currentUser?.email
    }

    // 🚪 LOGOUT
    fun logout() {
        auth.signOut()
    }
}