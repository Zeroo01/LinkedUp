package com.example.linkedup.data

import com.google.firebase.firestore.FirebaseFirestore


object JobRepository {


    private val db =
        FirebaseFirestore.getInstance()



    fun activateJob(

        jobId: String,

        recruiterId: String,

        eventId: String,

        title: String,

        callback: (Boolean) -> Unit

    ) {


        val job = hashMapOf(

            "jobId" to jobId,

            "recruiterId" to recruiterId,

            "eventId" to eventId,

            "title" to title,

            "active" to true,

            "createdAt" to System.currentTimeMillis()

        )



        db.collection("jobs")
            .document(jobId)
            .set(job)

            .addOnSuccessListener {

                callback(true)

            }

            .addOnFailureListener {

                callback(false)

            }

    }





    fun createJob(

        recruiterId: String,

        title: String,

        description: String,

        location: String,

        callback: (Boolean) -> Unit

    ) {



        val job = hashMapOf(

            "recruiterId" to recruiterId,

            "title" to title,

            "description" to description,

            "location" to location,

            "active" to false,

            "createdAt" to System.currentTimeMillis()

        )



        db.collection("jobs")

            .add(job)

            .addOnSuccessListener {

                callback(true)

            }

            .addOnFailureListener {

                callback(false)

            }


    }


}