package com.example.presentation.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.di.DependencyProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.chat.components.GlassBubble
import com.example.presentation.chat.components.MorphingInputBar
import com.example.ui.components.liquidGlass
import com.example.ui.theme.DeepCyan
import com.example.ui.theme.PitchBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = viewModel(factory = DependencyProvider.factory),
    onNavigateBack: () -> Unit,
    onNavigateToCall: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToCall) {
                        Icon(Icons.Default.Call, contentDescription = "Call")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PitchBlack.copy(alpha = 0.5f),
                    titleContentColor = DeepCyan,
                    navigationIconContentColor = DeepCyan,
                    actionIconContentColor = DeepCyan
                ),
                modifier = Modifier.liquidGlass(cornerRadius = 0.dp, borderWidth = 0.dp)
            )
        },
        bottomBar = {
            MorphingInputBar(
                onSendMessage = { text ->
                    // Determine chat ID from somewhere, assuming it's managed or passed
                    // Assuming for example first message's chat ID for brevity if messages exist
                    val placeholderId = state.messages.firstOrNull()?.chatId ?: "bot_chat"
                    viewModel.processIntent(ChatIntent.SendMessage(placeholderId, text, true))
                }
            )
        },
        containerColor = PitchBlack
    ) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(top = padding.calculateTopPadding(), bottom = padding.calculateBottomPadding() + 8.dp),
            modifier = Modifier.fillMaxSize(),
            reverseLayout = true
        ) {
            items(state.messages.reversed()) { message ->
                GlassBubble(message = message)
            }
        }
    }
}
