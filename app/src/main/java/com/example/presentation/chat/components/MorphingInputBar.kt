package com.example.presentation.chat.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.example.ui.components.liquidGlass
import com.example.ui.theme.DeepCyan
import com.example.ui.theme.TypographyPrimary

@Composable
fun MorphingInputBar(
    onSendMessage: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .liquidGlass(cornerRadius = 24.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            textStyle = androidx.compose.ui.text.TextStyle(color = TypographyPrimary),
            cursorBrush = SolidColor(DeepCyan)
        )
        
        IconButton(
            onClick = {
                if (text.isNotBlank()) {
                    onSendMessage(text)
                    text = ""
                }
            }
        ) {
            Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send", tint = DeepCyan)
        }
    }
}
