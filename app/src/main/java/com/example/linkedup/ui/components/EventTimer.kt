package com.example.linkedup.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun EventTimer(minutes: Long, seconds: Long) {

    Surface(
        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.15f),
        shape = MaterialTheme.shapes.large
    ) {
        Text(
            text = "LIVE %02d:%02d".format(minutes, seconds),
            modifier = androidx.compose.ui.Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
    }
}