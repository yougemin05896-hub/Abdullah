package com.example.presentation.chat.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.liquidGlass
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.TelegramBlue
import com.example.ui.theme.TypographyPrimary
import com.example.ui.theme.TypographySecondary

@Composable
fun GlassBubble(
    text: String,
    time: String,
    isMe: Boolean,
    isRead: Boolean = true
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 12.dp),
        contentAlignment = if (isMe) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .liquidGlass(
                    cornerRadius = 16.dp,
                    backgroundColor = if (isMe) TelegramBlue.copy(alpha = 0.2f) else com.example.ui.theme.AmoledLightGray,
                    borderColor = if (isMe) TelegramBlue.copy(alpha = 0.4f) else com.example.ui.theme.AmoledLightGray
                )
                .padding(12.dp)
        ) {
            Text(
                text = text,
                color = TypographyPrimary,
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = time,
                    color = TypographySecondary,
                    fontSize = 11.sp
                )
                if (isMe) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        Icons.Default.DoneAll,
                        contentDescription = "Read",
                        tint = if (isRead) NeonCyan else TypographySecondary,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}
