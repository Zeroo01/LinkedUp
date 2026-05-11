package com.example.linkedup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.linkedup.ui.theme.LinkedUpTheme

@Composable
fun HomeScreen() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {

            Text(
                text = "LinkedUp",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Recruiting Plattform für Karrieremessen",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {

            FeatureCard(
                title = "Lebenslauf hochladen",
                description = "Erstelle dein Profil und lade deinen CV hoch."
            )

            Spacer(modifier = Modifier.height(12.dp))

            FeatureCard(
                title = "Smart Matching",
                description = "Automatische 70–75% Übereinstimmung mit Jobs."
            )

            Spacer(modifier = Modifier.height(12.dp))

            FeatureCard(
                title = "Interaktive Events",
                description = "Reagiere live auf Stellenangebote auf Messen."
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {

            Text(
                text = "Aktuelle Events",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(
            listOf(
                "Tech Career Expo Berlin",
                "Startup Recruiting Day",
                "AI & Cloud Summit",
                "IT Job Fair 2026"
            )
        ) { event ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(16.dp)
            ) {

                Text(
                    text = event,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun FeatureCard(title: String, description: String) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = description)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LinkedUpTheme {
        HomeScreen()
    }
}