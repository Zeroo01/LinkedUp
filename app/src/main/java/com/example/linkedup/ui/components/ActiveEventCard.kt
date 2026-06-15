package com.example.linkedup.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linkedup.objects.Event

@Composable
fun ActiveEventCard(
    event: Event,
    timerContent: @Composable () -> Unit,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = onClick
    ) {

        Column {

            EventHeroSection(
                event = event,
                timerContent = timerContent
            )
        }
    }
}