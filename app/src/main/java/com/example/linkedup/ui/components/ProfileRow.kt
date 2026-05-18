package com.example.linkedup.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileRow(
    label: String,
    value: String,
    isEditing: Boolean,
    onValueChange: (String) -> Unit
) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(4.dp))

        if (isEditing) {

            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth()
            )

        } else {

            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(Modifier.height(12.dp))
    }
}