package com.example.linkedup.utils

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Offset

@Composable
fun Modifier.ambientGlow(): Modifier {

    val transition = rememberInfiniteTransition()

    val offsetX by transition.animateFloat(
        initialValue = -100f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val offsetY by transition.animateFloat(
        initialValue = -60f,
        targetValue = 60f,
        animationSpec = infiniteRepeatable(
            animation = tween(9000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    return this
        .background(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFF4FC3F7).copy(alpha = 0.25f),
                    Color(0xFF7C4DFF).copy(alpha = 0.15f),
                    Color.Transparent
                ),
                center = Offset(offsetX, offsetY),
                radius = 400f
            )
        )
        .blur(80.dp)
}