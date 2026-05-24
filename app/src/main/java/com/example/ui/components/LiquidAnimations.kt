package com.example.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring

object LiquidAnimations {
    val springSpec = spring<Float>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    )
    val floatSpringSpec = spring<Float>(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessMedium
    )
}
