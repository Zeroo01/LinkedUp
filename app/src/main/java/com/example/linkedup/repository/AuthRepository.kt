package com.example.linkedup.data

import com.example.linkedup.model.UserData
import com.example.linkedup.model.UserRole
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Login
    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String?, String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->

                val uid = result.user?.uid

                if (uid == null) {
                    onResult(false, "Benutzer-ID nicht gefunden", null)
                    return@addOnSuccessListener
                }

                firestore.collection("users")
                    .document(uid)
                    .get()
                    .addOnSuccessListener { document ->

                        val role = document.getString("role")

                        if (role != null) {
                            onResult(true, null, role)
                        } else {
                            onResult(false, "Keine Rolle gefunden", null)
                        }
                    }
                    .addOnFailureListener { error ->
                        onResult(false, error.message, null)
                    }
            }
            .addOnFailureListener { error ->
                onResult(false, error.message, null)
            }
    }

    // Registrierung
    fun register(
        email: String,
        password: String,
        role: String,
        onResult: (Boolean, String?, String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->

                val uid = result.user?.uid

                if (uid == null) {
                    onResult(false, "Benutzer-ID nicht gefunden", null)
                    return@addOnSuccessListener
                }

                val user = UserData(
                    uid = uid,
                    email = email,
                    role = UserRole.valueOf(role)
                )

                firestore.collection("users")
                    .document(uid)
                    .set(user)
                    .addOnSuccessListener {

                        onResult(
                            true,
                            null,
                            user.role.name
                        )
                    }
                    .addOnFailureListener { error ->

                        onResult(
                            false,
                            error.message,
                            null
                        )
                    }
            }
            .addOnFailureListener { error ->

                onResult(
                    false,
                    error.message,
                    null
                )
            }
    }

    // Rolle laden
    fun getUserRole(
        onResult: (String?) -> Unit
    ) {

        val uid = auth.currentUser?.uid

        if (uid == null) {
            onResult(null)
            return
        }

        firestore.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->

                val role = document.getString("role")
                onResult(role)
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

    // Session
    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun getCurrentEmail(): String? {
        return auth.currentUser?.email
    }

    fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }

    fun logout() {
        auth.signOut()
    }
}