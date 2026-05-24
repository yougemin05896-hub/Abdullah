package com.example.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.di.DependencyProvider
import com.example.presentation.chat.components.AttachmentBottomSheet
import com.example.presentation.chat.components.GlassBubble
import com.example.presentation.chat.components.MorphingInputBar
import com.example.ui.theme.PitchBlack
import com.example.ui.theme.TelegramBlue
import com.example.ui.theme.TypographyPrimary
import com.example.ui.theme.TypographySecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chatId: String,
    viewModel: ChatViewModel = viewModel(factory = DependencyProvider.factory),
    onNavigateBack: () -> Unit,
    onNavigateToProfile: () -> Unit = {},
    onNavigateToCall: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showAttachmentSheet by remember { mutableStateOf(false) }

    // Hardcode some preview messages imitating Telegram since local DB might be empty on fresh install
    val messages = listOf(
        Pair("Hello! Welcome to XGenam, the ultimate secure chat.", "12:00 PM"),
        Pair("How fast is the WebRTC component?", "12:01 PM"),
        Pair("Lightning fast! We are using Fly.io edge nodes.", "12:02 PM"),
        Pair("Can you share the encrypted payload?", "12:05 PM"),
        Pair("Sending it now over the secure channel.", "12:06 PM")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToProfile() }
                            .padding(vertical = 4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(TelegramBlue.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "X",
                                style = MaterialTheme.typography.titleMedium,
                                color = TelegramBlue,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("XGenam Secure Node", style = MaterialTheme.typography.titleMedium, color = TypographyPrimary)
                            Text("last seen recently", style = MaterialTheme.typography.labelSmall, color = TypographySecondary)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TypographyPrimary)
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToCall) {
                        Icon(Icons.Default.Call, contentDescription = "Call", tint = TypographyPrimary)
                    }
                    IconButton(onClick = { /* Menu */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More", tint = TypographyPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PitchBlack)
            )
        },
        bottomBar = {
            MorphingInputBar(
                onSend = { /* Send logic */ },
                onAttachClick = { showAttachmentSheet = true }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFF0A0A0A)) // Very dark background for high contrast
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                reverseLayout = true
            ) {
                // List rendered in reverse, so 0 is newest
                items(messages.reversed()) { msg ->
                    GlassBubble(
                        text = msg.first,
                        time = msg.second,
                        isMe = msg == messages.last() || msg == messages[2] // demo logic
                    )
                }
            }
        }
    }

    if (showAttachmentSheet) {
        AttachmentBottomSheet(onDismissRequest = { showAttachmentSheet = false })
    }
}
