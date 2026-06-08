package com.example.linkedup.data

import com.example.linkedup.objects.Profile
import com.google.firebase.firestore.FirebaseFirestore

class ProfileRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    fun loadProfile(email: String, onResult: (Profile) -> Unit) {
        db.collection("users")
            .document(email)
            .get()
            .addOnSuccessListener { doc ->
                val profile = doc.toObject(Profile::class.java) ?: Profile(email = email)
                onResult(profile)
            }
    }

    fun updateProfile(email: String, profile: Profile) {
        db.collection("users")
            .document(email)
            .set(profile)
    }

    fun createUserIfNotExists(email: String) {
        val ref = db.collection("users").document(email)

        ref.get().addOnSuccessListener { doc ->
            if (!doc.exists()) {
                ref.set(Profile(email = email))
            }
        }
    }
}