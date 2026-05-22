package com.example.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring

object LiquidAnimations {
    val liquidSpringProperties = spring<Float>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    )

    fun <T> springSpec() = spring<T>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    )
    
    fun <T> stiffSpringSpec() = spring<T>(
        dampingRatio = 0.8f,
        stiffness = Spring.StiffnessHigh
    )
}
