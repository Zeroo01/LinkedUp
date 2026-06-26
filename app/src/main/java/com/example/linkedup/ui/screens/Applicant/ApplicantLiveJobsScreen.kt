package com.example.linkedup.ui.screens.Applicant

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.linkedup.data.ApplicationRepository
import com.example.linkedup.data.AuthRepository
import com.example.linkedup.sensor.HandRaiseDetector
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration


data class LiveJob(

    val id: String = "",

    val title: String = "",

    val recruiterId: String = "",

    val eventId: String = ""

)



@Composable
fun ApplicantLiveJobsScreen() {


    var liveJobs by remember {
        mutableStateOf<List<LiveJob>>(emptyList())
    }


    var message by remember {
        mutableStateOf("")
    }


    val context = LocalContext.current



    DisposableEffect(Unit) {


        val listener: ListenerRegistration =

            FirebaseFirestore
                .getInstance()
                .collection("jobs")
                .whereEqualTo(
                    "status",
                    "LIVE"
                )
                .addSnapshotListener { snapshot, _ ->


                    if(snapshot != null) {


                        liveJobs =
                            snapshot.documents.map { document ->


                                LiveJob(

                                    id = document.id,

                                    title =
                                        document.getString("title")
                                            ?: "",


                                    recruiterId =
                                        document.getString("recruiterId")
                                            ?: "",


                                    eventId =
                                        document.getString("eventId")
                                            ?: ""

                                )

                            }

                    }

                }



        onDispose {

            listener.remove()

        }

    }



    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {


        item {


            Text(

                text = "Aktive Messe-Stellen",

                style =
                    MaterialTheme.typography.headlineMedium

            )


            Spacer(
                modifier = Modifier.height(20.dp)
            )

        }



        if(liveJobs.isEmpty()) {


            item {


                Text(
                    text =
                        "Aktuell sind keine Stellen live."
                )

            }


        } else {


            items(liveJobs) { job ->



                LiveJobCard(

                    job = job,

                    context = context,

                    onApplicationSent = {

                        message =
                            "Bewerbung wurde gesendet"

                    }

                )


            }

        }



        item {


            if(message.isNotEmpty()) {


                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.primary
                )

            }

        }

    }

}






@Composable
private fun LiveJobCard(

    job: LiveJob,

    context: Context,

    onApplicationSent: () -> Unit

) {


    fun sendApplication(){


        val applicantId =
            AuthRepository.getCurrentUserId()



        if(applicantId != null){


            ApplicationRepository.sendApplication(

                applicantId = applicantId,

                jobId = job.id,

                recruiterId = job.recruiterId

            )


            onApplicationSent()

        }

    }



    var detector by remember {
        mutableStateOf<HandRaiseDetector?>(null)
    }



    DisposableEffect(Unit) {


        val handDetector = HandRaiseDetector(

            context = context

        ){

            sendApplication()

        }



        detector = handDetector


        handDetector.start()



        onDispose {

            handDetector.stop()

        }

    }





    Card(

        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)

    ){



        Column(

            modifier =
                Modifier.padding(16.dp)

        ){



            Text(

                text = job.title,

                style =
                    MaterialTheme.typography.titleMedium

            )



            Spacer(
                modifier = Modifier.height(8.dp)
            )



            Text(

                text =
                    "Diese Stelle ist gerade auf der Messe aktiv."

            )



            Spacer(
                modifier = Modifier.height(16.dp)
            )



            Button(

                onClick = {

                    sendApplication()

                },

                modifier =
                    Modifier.fillMaxWidth()

            ){


                Text(
                    "Hand heben"
                )

            }


        }

    }

}