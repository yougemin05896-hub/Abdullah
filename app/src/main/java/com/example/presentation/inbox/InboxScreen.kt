package com.example.presentation.inbox

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.di.DependencyProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.inbox.components.ChatItem
import com.example.ui.components.liquidGlass
import com.example.ui.theme.DeepCyan
import com.example.ui.theme.PitchBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InboxScreen(
    viewModel: InboxViewModel = viewModel(factory = DependencyProvider.factory),
    onNavigateToChat: (String) -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
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
            items(state.chats) { chat ->
                ChatItem(chat = chat, onClick = { onNavigateToChat(chat.id) })
            }
        }
    }
}
