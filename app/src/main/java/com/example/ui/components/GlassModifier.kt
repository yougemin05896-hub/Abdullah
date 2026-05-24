package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.NeonCyan

fun Modifier.liquidGlass(
    cornerRadius: Dp = 16.dp,
    borderWidth: Dp = 1.dp,
    backgroundColor: Color = Color(0x1AFFFFFF),
    borderColor: Color = NeonCyan.copy(alpha = 0.4f)
) = composed {
    this
        .clip(RoundedCornerShape(cornerRadius))
        .background(backgroundColor)
        .border(borderWidth, borderColor, RoundedCornerShape(cornerRadius))
}
