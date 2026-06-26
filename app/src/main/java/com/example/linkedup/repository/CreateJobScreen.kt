package com.example.linkedup.ui.screens.Recruiter

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linkedup.data.AuthRepository
import com.example.linkedup.data.JobRepository


@Composable
fun CreateJobScreen(
    onBack: () -> Unit
) {


    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var location by remember {
        mutableStateOf("")
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
            text = "Neue Stelle erstellen",
            style = MaterialTheme.typography.headlineMedium
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )



        OutlinedTextField(

            value = title,

            onValueChange = {
                title = it
            },

            label = {
                Text("Jobtitel")
            },

            modifier = Modifier.fillMaxWidth()

        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )



        OutlinedTextField(

            value = description,

            onValueChange = {
                description = it
            },

            label = {
                Text("Beschreibung")
            },

            modifier = Modifier.fillMaxWidth(),

            minLines = 4

        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )



        OutlinedTextField(

            value = location,

            onValueChange = {
                location = it
            },

            label = {
                Text("Ort")
            },

            modifier = Modifier.fillMaxWidth()

        )



        Spacer(
            modifier = Modifier.height(20.dp)
        )



        Button(

            onClick = {


                val recruiterId =
                    AuthRepository.getCurrentUserId()



                if(recruiterId != null) {


                    JobRepository.createJob(

                        recruiterId = recruiterId,

                        title = title,

                        description = description,

                        location = location


                    ) { success ->


                        if(success) {

                            message =
                                "Stelle erfolgreich erstellt"

                            title = ""
                            description = ""
                            location = ""

                        } else {

                            message =
                                "Fehler beim Erstellen"

                        }


                    }


                } else {


                    message =
                        "Kein Benutzer angemeldet"


                }



            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Stelle erstellen")

        }



        Spacer(
            modifier = Modifier.height(12.dp)
        )



        Button(

            onClick = onBack,

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Zurück")

        }



        if(message.isNotEmpty()) {

            Spacer(
                modifier = Modifier.height(16.dp)
            )


            Text(
                text = message,
                color = MaterialTheme.colorScheme.primary
            )

        }


    }


}