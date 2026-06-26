package com.example.linkedup.interfaces

import com.example.linkedup.objects.CandidateCv

interface CvTransferApi {

    fun createSession(onResult: (String) -> Unit)

    fun joinSession(sessionId: String, userId: String)

    fun sendCv(
        sessionId: String,
        userId: String,
        cvUrl: String
    )

    fun observeCandidates(
        sessionId: String,
        onUpdate: (List<CandidateCv>) -> Unit
    )
}