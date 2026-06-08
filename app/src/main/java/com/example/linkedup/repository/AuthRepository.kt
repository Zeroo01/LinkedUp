package com.example.linkedup.data

import com.example.linkedup.model.UserData
import com.example.linkedup.model.UserRole
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Einloggen
    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String?, String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->

                val uid = result.user?.uid ?: return@addOnSuccessListener

                firestore.collection("users")
                    .document(uid)
                    .get()
                    .addOnSuccessListener { doc ->

                        val role = doc.getString("role")

                        if (role != null) {
                            onResult(true, null, role)
                        } else {
                            onResult(false, "Role nicht gefunden", null)
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

                val uid = result.user?.uid ?: return@addOnSuccessListener

                val user = UserData(
                    uid = uid,
                    email = email,
                    role = UserRole.valueOf(role)
                )

                firestore.collection("users")
                    .document(uid)
                    .set(user)
                    .addOnSuccessListener {
                        onResult(true, null, user.role.name)
                    }
                    .addOnFailureListener { error ->
                        onResult(false, error.message, null)
                    }
            }
            .addOnFailureListener { error ->
                onResult(false, error.message, null)
            }
    }

    // UTILS
    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun getCurrentEmail(): String? {
        return auth.currentUser?.email
    }

    fun logout() {
        auth.signOut()
    }
}