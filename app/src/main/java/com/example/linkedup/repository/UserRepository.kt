package com.example.linkedup.repository

import com.example.linkedup.model.UserData
import com.google.firebase.firestore.FirebaseFirestore

object UserRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun createUser(user: UserData) {

        firestore.collection("users")
            .document(user.uid)
            .set(user)
    }

    fun getUser(
        uid: String,
        onResult: (UserData?) -> Unit
    ) {

        firestore.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { doc ->

                if (doc.exists()) {

                    val user = UserData(
                        uid = doc.getString("uid") ?: "",
                        email = doc.getString("email") ?: "",
                        role = com.example.linkedup.model.UserRole.valueOf(
                            doc.getString("role") ?: "APPLICANT"
                        )
                    )

                    onResult(user)

                } else {
                    onResult(null)
                }
            }
            .addOnFailureListener {
                onResult(null)
            }
    }
}