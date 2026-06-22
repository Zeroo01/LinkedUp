package com.example.linkedup.ui.screens.eventmanager

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class EventOverview(
    val title: String,
    val companies: Int,
    val visitors: Int
)

@Composable
fun CurrentEventsScreen() {

    val events = listOf(

        EventOverview(
            "Karrieremesse München",
            25,
            600
        ),

        EventOverview(
            "IT Connect Berlin",
            10,
            300
        ),

        EventOverview(
            "Career Day Hamburg",
            7,
            150
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {

            Text(
                text = "Aktuelle Events",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(events) { event ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = event.title,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Unternehmen: ${event.companies}"
                    )

                    Text(
                        text = "Besucher: ${event.visitors}"
                    )
                }
            }
        }
    }
}