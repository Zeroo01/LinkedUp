package com.example.linkedup.ui.screens.EventManager

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.linkedup.objects.Event
import com.example.linkedup.objects.Presentation
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

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

            Spacer(Modifier.height(20.dp))
        }

        items(event.presentations) { presentation ->

            PresentationCard(
                presentation = presentation
            )
        }
    }
}

@Composable
fun PresentationCard(
    presentation: Presentation
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable {
                expanded = !expanded
            }
            .animateContentSize(),
        shape = RoundedCornerShape(24.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Column {

                    Text(
                        text = presentation.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = presentation.time,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            if (expanded) {

                Spacer(Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .background(
                            MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(20.dp)
                        )
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = presentation.speaker,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = presentation.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}