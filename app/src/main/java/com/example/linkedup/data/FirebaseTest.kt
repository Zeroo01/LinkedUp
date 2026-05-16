package com.example.linkedup.data

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseTest {

    private val db = FirebaseFirestore.getInstance()

    fun writeTestUser() {

        val user = hashMapOf(
            "name" to "Max Mustermann",
            "role" to "candidate",
            "skills" to listOf("Kotlin", "Compose", "Firebase")
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                println("✅ FIREBASE OK: ${it.id}")
            }
            .addOnFailureListener {
                println("❌ FIREBASE ERROR: ${it.message}")
            }
    }
}