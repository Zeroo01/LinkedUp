package com.example.linkedup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.linkedup.objects.Event
import com.example.linkedup.objects.EventItem

@Composable
fun EventDetailScreen(
    event: Event,
    onBack: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = event.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                TextButton(onClick = onBack) {
                    Text("Back")
                }
            }

            Spacer(Modifier.height(16.dp))
        }

        items(event.timeline) { item ->
            TimelineCard(item)
        }
    }
}

@Composable
fun TimelineCard(item: EventItem) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = item.time,
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = item.title,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = item.type,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}