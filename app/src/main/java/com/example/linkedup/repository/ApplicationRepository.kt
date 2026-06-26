package com.example.linkedup.data

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore


object ApplicationRepository {


    private val firestore =
        FirebaseFirestore.getInstance()



    fun sendApplication(

        applicantId: String,

        jobId: String,

        recruiterId: String

    ) {


        val application = hashMapOf(

            "applicantId" to applicantId,

            "jobId" to jobId,

            "recruiterId" to recruiterId,

            "status" to "NEW",

            "timestamp" to FieldValue.serverTimestamp()

        )



        firestore
            .collection("applications")
            .add(application)

    }

}