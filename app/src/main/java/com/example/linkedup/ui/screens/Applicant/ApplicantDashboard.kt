package com.example.linkedup.ui.screens.Applicant

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.linkedup.ui.components.DashboardCard

@Composable
fun ApplicantDashboard(
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
                text = "LinkedUp",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Dein persönliches Karriere Dashboard",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        // PROFILE STATUS CARD (HERO)
        item {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = "Profil Fortschritt",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    LinearProgressIndicator(
                        progress = { 0.72f },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "72% vollständig – verbessere dein Profil für bessere Matches",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

        // MATCH CARD
        item {

            DashboardCard(
                title = "Top Match",
                description = "SAP Deutschland – 92% Übereinstimmung mit deinem Profil"
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        // CV CARD
        item {

            DashboardCard(
                title = "Lebenslauf",
                description = "Kein Dokument hochgeladen. Upload aktivieren für bessere Vorschläge."
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        // EVENTS CARD
        item {

            DashboardCard(
                title = "Aktive Events",
                description = "2 Karrieremessen in deiner Region verfügbar"
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        // ACTION SECTION
        item {

            Text(
                text = "Schnellaktionen",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        item {

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Lebenslauf hochladen")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Matches anzeigen")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Events entdecken")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}