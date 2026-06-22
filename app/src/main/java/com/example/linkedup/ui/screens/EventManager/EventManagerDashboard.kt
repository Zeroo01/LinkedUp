package com.example.linkedup.ui.screens.eventmanager

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.linkedup.ui.components.DashboardCard


@Composable
fun EventManagerDashboard(
    onLogout: () -> Unit
) {

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        // HEADER
        item {

            Text(
                text = "Event Manager",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )


            Spacer(
                modifier = Modifier.height(6.dp)
            )


            Text(
                text = "Verwalte deine Karrieremessen und Events",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )


            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }



        // AKTIVE EVENTS
        item {

            DashboardCard(

                title = "Aktive Events",

                description =
                    "3 Karrieremessen sind aktuell aktiv."
            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }



        // UNTERNEHMEN
        item {

            DashboardCard(

                title = "Teilnehmende Unternehmen",

                description =
                    "42 Unternehmen nehmen aktuell an deinen Events teil."
            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }



        // BESUCHER
        item {

            DashboardCard(

                title = "Registrierte Besucher",

                description =
                    "1.286 Bewerber haben sich für deine Events angemeldet."
            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }



        // NÄCHSTES EVENT
        item {

            DashboardCard(

                title = "Nächstes Event",

                description =
                    "Karrieremesse Berlin – 15. September 2026"
            )


            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }



        // SCHNELLAKTIONEN
        item {

            Text(

                text = "Schnellaktionen",

                style = MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.SemiBold
            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }



        item {

            Button(

                onClick = { },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text(
                    "Neues Event erstellen"
                )
            }


            Spacer(
                modifier = Modifier.height(8.dp)
            )



            Button(

                onClick = { },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text(
                    "Teilnehmer verwalten"
                )
            }


            Spacer(
                modifier = Modifier.height(8.dp)
            )



            Button(

                onClick = { },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text(
                    "Unternehmen verwalten"
                )
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }



        // LOGOUT
        item {

            Button(

                onClick = onLogout,

                modifier = Modifier.fillMaxWidth(),

                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )

            ) {

                Text(
                    "Abmelden"
                )
            }
        }
    }
}