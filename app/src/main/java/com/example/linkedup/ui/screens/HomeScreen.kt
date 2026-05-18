package com.example.linkedup.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.linkedup.App
import com.example.linkedup.objects.Event
import com.example.linkedup.repository.EventRepository
import com.example.linkedup.ui.theme.LinkedUpTheme
import androidx.compose.runtime.*
import com.example.linkedup.utils.ambientGlow
import com.example.linkedup.utils.animatedBorderBrush
import com.example.linkedup.utils.eventTimer
import java.util.concurrent.TimeUnit
import com.example.linkedup.utils.glow
@Composable
fun HomeScreen(onEventClick: (Event) -> Unit) {

    val activeEvent = remember {
        EventRepository.getActiveEvent()
    }

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

            Spacer(Modifier.height(16.dp))
        }

        item {

            activeEvent?.let { event ->

                ActiveEventCard(
                    event = event,
                    onClick = { onEventClick(event) }
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun ActiveEventCard(
    event: Event,
    onClick: () -> Unit
) {

    val timerFlow = remember(event.id) {
        eventTimer(System.currentTimeMillis())
    }

    val elapsedTime by timerFlow.collectAsState(initial = 0L)

    val minutes = java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(elapsedTime)
    val seconds = java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(elapsedTime) % 60

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(28.dp),
        onClick = onClick
    ) {

        Column {

            // HERO
            val borderBrush = animatedBorderBrush()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(1.dp) //grauer rand

            ) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .glow()
                )

                // ANIMATED BORDER
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            animatedBorderBrush(),
                            RoundedCornerShape(28.dp)
                        )
                )

                // INNER CARD
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(4.dp)
                        .background(
                            MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(26.dp)
                        )
                )

                // CONTENT
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = event.title,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.weight(1f)
                        )

                        Surface(
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(50)
                        ) {
                            Text(
                                text = "LIVE %02d:%02d".format(minutes, seconds),
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = event.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                    )

                    Spacer(Modifier.height(10.dp))

                    Text(
                        text = "Tap to view timeline →",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                    )
                }
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
