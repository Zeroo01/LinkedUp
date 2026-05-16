package com.example.linkedup.utils

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.*
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun animatedBorderBrush(): Brush {

    val transition = rememberInfiniteTransition()

    val angle by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing)
        )
    )

    return remember(angle) {

        val rad = Math.toRadians(angle.toDouble())

        Brush.linearGradient(
            colors = listOf(
                Color(0xFF2133DE),
                Color(0xFF0088FF),
                Color(0xFF1C63F3),
                Color(0xFF258EC5)
            ),
            start = androidx.compose.ui.geometry.Offset(
                (cos(rad) * 100f).toFloat(),
                (sin(rad) * 100f).toFloat()
            ),
            end = androidx.compose.ui.geometry.Offset(
                (cos(rad + Math.PI).toFloat() * 100f),
                (sin(rad + Math.PI).toFloat() * 100f)
            )
        )
    }
}