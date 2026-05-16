package com.example.linkedup.utils
//Das ist für glow effekt btw

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background

fun Modifier.glow(): Modifier {
    return this.background(
        Brush.radialGradient(
            colors = listOf(
                Color(0x552196F3),
                Color.Transparent
            )
        )
    )
}