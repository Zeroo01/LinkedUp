package com.example.linkedup.repository

import com.example.linkedup.interfaces.CvTransferApi
import com.example.linkedup.objects.CandidateCv
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

object CvTransferRepository : CvTransferApi {

    private val db = FirebaseFirestore.getInstance()

    override fun createSession(onResult: (String) -> Unit) {

        val sessionId = UUID.randomUUID().toString()

        val sessionData = hashMapOf(
            "createdAt" to System.currentTimeMillis(),
            "active" to true
        )

        db.collection("sessions")
            .document(sessionId)
            .set(sessionData)
            .addOnSuccessListener {
                onResult(sessionId)
            }
    }

    override fun joinSession(sessionId: String, userId: String) {
        // optional später
    }

    override fun sendCv(
        sessionId: String,
        userId: String,
        cvUrl: String
    ) {

        val data = hashMapOf(
            "userId" to userId,
            "cvUrl" to cvUrl,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("sessions")
            .document(sessionId)
            .collection("candidates")
            .document(userId)
            .set(data)
    }

    override fun observeCandidates(
        sessionId: String,
        onUpdate: (List<CandidateCv>) -> Unit
    ) {

        db.collection("sessions")
            .document(sessionId)
            .collection("candidates")
            .addSnapshotListener { snapshot, _ ->

                val list = snapshot?.documents?.mapNotNull { doc ->

                    CandidateCv(
                        userId = doc.getString("userId") ?: "",
                        name = doc.getString("name") ?: "",
                        cvUrl = doc.getString("cvUrl") ?: "",
                        timestamp = doc.getLong("timestamp") ?: 0L
                    )
                } ?: emptyList()

                onUpdate(list)
            }
    }
}