package com.example.presentation.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ui.theme.AmoledLightGray
import com.example.ui.theme.PitchBlack
import com.example.ui.theme.TelegramBlue
import com.example.ui.theme.TypographyPrimary
import com.example.ui.theme.TypographySecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MorphingInputBar(
    onSend: (String) -> Unit,
    onAttachClick: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(PitchBlack)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
                .background(AmoledLightGray)
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                IconButton(onClick = { /* Emoji */ }, modifier = Modifier.align(Alignment.Bottom)) {
                    Icon(Icons.Default.EmojiEmotions, contentDescription = "Emoji", tint = TypographySecondary)
                }
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 50.dp, max = 150.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = TypographyPrimary,
                        unfocusedTextColor = TypographyPrimary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text("Message", color = TypographySecondary) }
                )
                IconButton(onClick = onAttachClick, modifier = Modifier.align(Alignment.Bottom)) {
                    Icon(Icons.Default.AttachFile, contentDescription = "Attach", tint = TypographySecondary)
                }
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        val isTyping = text.isNotBlank()
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(TelegramBlue),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { if (isTyping) { onSend(text); text = "" } else { /* Record Voice */ } }) {
                Icon(
                    if (isTyping) Icons.AutoMirrored.Filled.Send else Icons.Default.Mic, 
                    contentDescription = "Action", 
                    tint = TypographyPrimary
                )
            }
        }
    }
}
