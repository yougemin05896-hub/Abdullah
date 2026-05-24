package com.example.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.presentation.chat.components.AttachmentBottomSheet
import com.example.presentation.chat.components.GlassBubble
import com.example.presentation.chat.components.MorphingInputBar
import com.example.ui.theme.PitchBlack
import com.example.ui.theme.TypographyPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedMessagesScreen(onNavigateBack: () -> Unit) {
    var showAttachmentSheet by remember { mutableStateOf(false) }

    val messages = listOf(
        Pair("Note: Check the WebRTC connection logs.", "Yesterday"),
        Pair("https://fly.io/docs", "Yesterday"),
        Pair("Save this encrypted payload for later.", "10:00 AM")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Saved Messages", style = MaterialTheme.typography.titleMedium, color = TypographyPrimary) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TypographyPrimary)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = TypographyPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PitchBlack)
            )
        },
        bottomBar = {
            MorphingInputBar(
                onSend = { /* Save logic */ },
                onAttachClick = { showAttachmentSheet = true }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFF0A0A0A))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                reverseLayout = true
            ) {
                items(messages.reversed()) { msg ->
                    GlassBubble(
                        text = msg.first,
                        time = msg.second,
                        isMe = true // Saved messages always appear on one side typically, or stylized
                    )
                }
            }
        }
    }

    if (showAttachmentSheet) {
        AttachmentBottomSheet(onDismissRequest = { showAttachmentSheet = false })
    }
}
