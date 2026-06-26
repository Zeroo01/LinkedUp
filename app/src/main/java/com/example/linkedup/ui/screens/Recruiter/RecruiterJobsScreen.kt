package com.example.linkedup.ui.screens.Recruiter

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linkedup.data.AuthRepository
import com.example.linkedup.data.JobRepository


@Composable
fun RecruiterJobsScreen(
    onCreateJob: () -> Unit
) {


    var isLive by remember {
        mutableStateOf(false)
    }


    var message by remember {
        mutableStateOf("")
    }



    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {



        Text(

            text = "Stellenverwaltung",

            style = MaterialTheme.typography.headlineMedium

        )



        Spacer(
            modifier = Modifier.height(20.dp)
        )



        Card(

            modifier = Modifier.fillMaxWidth()

        ) {


            Column(

                modifier = Modifier.padding(16.dp)

            ) {


                Text(

                    text = "Android Entwickler",

                    style = MaterialTheme.typography.titleMedium

                )



                Spacer(
                    modifier = Modifier.height(8.dp)
                )



                Text(
                    text =
                        if (isLive)
                            "Status: Stelle ist aktuell live"
                        else
                            "Status: Stelle ist nicht aktiv"
                )



                Spacer(
                    modifier = Modifier.height(16.dp)
                )



                Button(

                    onClick = {


                        val recruiterId =
                            AuthRepository.getCurrentUserId()



                        if (recruiterId != null) {


                            JobRepository.activateJob(

                                jobId = "android_dev_001",

                                recruiterId = recruiterId,

                                eventId = "messe_berlin_2026",

                                title = "Android Entwickler"

                            ) { success ->



                                if(success) {

                                    isLive = true

                                    message =
                                        "Stelle wurde aktiviert"

                                } else {

                                    message =
                                        "Fehler beim Aktivieren"

                                }

                            }


                        } else {


                            message =
                                "Kein Benutzer angemeldet"


                        }

                    },

                    modifier = Modifier.fillMaxWidth()

                ) {


                    Text(

                        if(isLive)
                            "Stelle ist aktiv"
                        else
                            "Stelle live schalten"

                    )

                }

            }

        }



        Spacer(
            modifier = Modifier.height(20.dp)
        )



        if(message.isNotEmpty()) {


            Text(

                text = message,

                color = MaterialTheme.colorScheme.primary

            )

        }



        Spacer(
            modifier = Modifier.height(24.dp)
        )



        Button(

            onClick = onCreateJob,

            modifier = Modifier.fillMaxWidth()

        ) {

            Text(
                "Neue Stelle anlegen"
            )

        }



        Spacer(
            modifier = Modifier.height(12.dp)
        )



        Button(

            onClick = { },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text(
                "Bewerbungen anzeigen"
            )

        }

    }
}