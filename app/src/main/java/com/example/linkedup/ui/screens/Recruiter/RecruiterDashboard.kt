package com.example.linkedup.ui.screens.Recruiter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.linkedup.ui.components.DashboardCard

@Composable
fun RecruiterDashboard(
    onLogout: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {

            Text(
                text = "Recruiter Dashboard",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Verwalte Bewerber und Stellenangebote",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        item {

            DashboardCard(
                title = "Offene Stellen",
                description = "12 Stellen sind aktuell veröffentlicht."
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        item {

            DashboardCard(
                title = "Neue Bewerbungen",
                description = "18 neue Bewerbungen warten auf deine Prüfung."
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        item {

            DashboardCard(
                title = "Top Kandidaten",
                description = "5 Bewerber haben eine Übereinstimmung von über 90 %."
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

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
                Text("Neue Stelle erstellen")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Bewerber durchsuchen")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Events anzeigen")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {

            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Abmelden")
            }
        }
    }
}