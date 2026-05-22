package com.example.presentation.chatlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.entities.ChatEntity
import com.example.ui.components.liquidGlass
import com.example.ui.theme.DeepCyan
import com.example.ui.theme.PitchBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(
    viewModel: ChatListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ChatListViewModel.provideFactory()),
    onNavigateToChat: (String) -> Unit
) {
    val chats by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Glass Messenger") },
                navigationIcon = {
                    IconButton(onClick = { /* Open drawer */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PitchBlack.copy(alpha = 0.5f),
                    titleContentColor = DeepCyan,
                    navigationIconContentColor = DeepCyan,
                    actionIconContentColor = DeepCyan
                ),
                modifier = Modifier.liquidGlass(
                    cornerRadius = 0.dp,
                    fillColor = PitchBlack.copy(alpha = 0.3f),
                    borderWidth = 0.dp
                )
            )
        },
        containerColor = PitchBlack
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize()
        ) {
            items(chats.chats.ifEmpty { dummyChats() }) { chat ->
                ChatItem(chat = chat, onClick = { onNavigateToChat(chat.id) })
            }
        }
    }
}

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
                .size(56.dp)
                .clip(CircleShape)
                .liquidGlass(
                    cornerRadius = 28.dp,
                    fillColor = DeepCyan.copy(alpha = 0.2f),
                    borderWidth = 1.dp
                )
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = chat.name,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chat.lastMessage ?: "",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                fontSize = 14.sp,
                maxLines = 1
            )
        }
        if (chat.unreadCount > 0) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(DeepCyan, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = chat.unreadCount.toString(),
                    color = PitchBlack,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}

private fun dummyChats() = listOf(
    ChatEntity("1", "Alice Glass", null, "Are you seeing this UI?", System.currentTimeMillis(), 2),
    ChatEntity("2", "Bob Matrix", null, "The liquid glass looks crazy.", System.currentTimeMillis(), 0),
    ChatEntity("3", "Cipher Group", null, "New WebRTC updates pushed.", System.currentTimeMillis(), 5, isGroup = true)
)
