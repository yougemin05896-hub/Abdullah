package com.example.presentation.inbox.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.local.entities.ChatEntity
import com.example.ui.components.liquidGlass
import com.example.ui.theme.DeepCyan
import com.example.ui.theme.PitchBlack
import com.example.ui.theme.TypographyPrimary
import com.example.ui.theme.TypographySecondary

@Composable
fun ChatItem(chat: ChatEntity, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .liquidGlass(cornerRadius = 26.dp, borderWidth = 1.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = chat.name.take(1).uppercase(),
                color = DeepCyan
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = chat.name,
                color = TypographyPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chat.lastMessage ?: "",
                color = TypographySecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        
        if (chat.unreadCount > 0) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(DeepCyan),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = chat.unreadCount.toString(),
                    color = PitchBlack
                )
            }
        }
    }
}
