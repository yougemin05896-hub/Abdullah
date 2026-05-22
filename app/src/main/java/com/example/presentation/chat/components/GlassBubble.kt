package com.example.presentation.chat.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.local.entities.MessageEntity
import com.example.ui.components.liquidGlass
import com.example.ui.theme.DeepCyan
import com.example.ui.theme.GlassDarkGrey
import com.example.ui.theme.TypographyPrimary

@Composable
fun GlassBubble(message: MessageEntity) {
    val alignment = if (message.isOutgoing) Alignment.CenterEnd else Alignment.CenterStart
    val bubbleColor = if (message.isOutgoing) DeepCyan.copy(alpha = 0.2f) else GlassDarkGrey.copy(alpha = 0.5f)
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        contentAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .liquidGlass(
                    cornerRadius = 16.dp,
                    fillColor = bubbleColor
                )
                .padding(12.dp)
        ) {
            Text(
                text = message.text,
                color = TypographyPrimary
            )
        }
    }
}
