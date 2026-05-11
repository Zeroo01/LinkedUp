package com.example.linkedup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class MatchItem(
    val company: String,
    val role: String,
    val matchPercent: Int
)

@Composable
fun MatchesScreen() {

    val matches = listOf(
        MatchItem("BMW Group", "Android Developer", 78),
        MatchItem("SAP", "Software Engineer", 82),
        MatchItem("Siemens", "Cloud Developer", 74),
        MatchItem("Bosch", "IT Consultant", 88)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {

            Text(
                text = "Deine Matches",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Unternehmen mit hoher Übereinstimmung (70–75%+)",
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        items(matches) { match ->

            MatchCard(match)

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun MatchCard(match: MatchItem) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = match.company,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = match.role)

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { match.matchPercent / 100f },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${match.matchPercent}% Match"
            )
        }
    }
}