package com.example.linkedup.data

import com.google.firebase.firestore.FirebaseFirestore

object ProfileRepository {

    private val db = FirebaseFirestore.getInstance()

    fun loadProfile(
        email: String,
        onResult: (Map<String, Any>) -> Unit
    ) {
        db.collection("users")
            .document(email)
            .get()
            .addOnSuccessListener { doc ->
                onResult(doc.data ?: emptyMap())
            }
    }

    fun createUserIfNotExists(email: String) {

        val userRef = db.collection("users").document(email)

        userRef.get().addOnSuccessListener { doc ->

            if (!doc.exists()) {

                val newUser: Map<String, Any> = hashMapOf(
                    "firstName" to "",
                    "lastName" to "",
                    "email" to email,
                    "jobTitle" to "",
                    "skills" to "",
                    "interests" to ""
                )

                userRef.set(newUser)
            }
        }
    }

    fun updateProfile(
        email: String,
        firstName: String,
        lastName: String,
        jobTitle: String,
        skills: String,
        interests: String
    ) {

        val updatedData: Map<String, Any> = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "jobTitle" to jobTitle,
            "skills" to skills,
            "interests" to interests
        )

        db.collection("users")
            .document(email)
            .update(updatedData)
    }
}