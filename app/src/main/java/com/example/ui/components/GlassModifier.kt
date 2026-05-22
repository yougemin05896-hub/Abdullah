package com.example.ui.components

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.liquidGlass(
    cornerRadius: Dp = 16.dp,
    blurRadius: Dp = 16.dp,
    fillColor: Color = Color(0x08FFFFFF),
    borderColorStart: Color = Color(0x44FFFFFF),
    borderColorEnd: Color = Color(0x11FFFFFF),
    borderWidth: Dp = 0.5.dp
): Modifier = composed {
    val shape = RoundedCornerShape(cornerRadius)
    this
        .graphicsLayer {
            clip = true
            this.shape = shape
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                renderEffect = androidx.compose.ui.graphics.BlurEffect(
                    radiusX = blurRadius.toPx(),
                    radiusY = blurRadius.toPx(),
                    edgeTreatment = androidx.compose.ui.graphics.TileMode.Clamp
                )
            }
        }
        .then(if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) Modifier.blur(blurRadius, BlurredEdgeTreatment(shape)) else Modifier)
        .background(fillColor, shape)
        .border(
            width = borderWidth,
            brush = Brush.linearGradient(listOf(borderColorStart, borderColorEnd)),
            shape = shape
        )
        .clip(shape)
}
