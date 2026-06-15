package com.example.linkedup.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.example.linkedup.objects.Event
import com.example.linkedup.utils.animatedBorderBrush
import com.example.linkedup.utils.glow

@Composable
fun EventHeroSection(
    event: Event,
    timerContent: @Composable () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(1.dp)
    ) {

        Box(Modifier.matchParentSize().glow())

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    animatedBorderBrush(),
                    shape = MaterialTheme.shapes.large
                )
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(4.dp)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.large
                )
        )

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
                    modifier = Modifier.weight(1f)
                )

                timerContent()
            }

            Spacer(Modifier.height(6.dp))

            Text(
                text = event.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Tap to view timeline →",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}