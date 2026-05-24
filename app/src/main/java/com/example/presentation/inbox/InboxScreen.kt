package com.example.presentation.inbox

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.di.DependencyProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.inbox.components.ChatItem
import com.example.ui.components.liquidGlass
import com.example.ui.theme.DeepCyan
import com.example.ui.theme.PitchBlack
import com.example.ui.theme.TypographyPrimary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InboxScreen(
    viewModel: InboxViewModel = viewModel(factory = DependencyProvider.factory),
    onNavigateToChat: (String) -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToFolders: () -> Unit,
    onNavigateToBotStore: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = PitchBlack,
                drawerContentColor = TypographyPrimary,
                modifier = Modifier.width(280.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                NavigationDrawerItem(
                    label = { Text("Settings", color = TypographyPrimary) },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; onNavigateToSettings() },
                    icon = { Icon(Icons.Default.Settings, contentDescription = null, tint = DeepCyan) },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = PitchBlack)
                )
                NavigationDrawerItem(
                    label = { Text("Chat Folders", color = TypographyPrimary) },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; onNavigateToFolders() },
                    icon = { Icon(Icons.Default.Folder, contentDescription = null, tint = DeepCyan) },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = PitchBlack)
                )
                NavigationDrawerItem(
                    label = { Text("Bot Store", color = TypographyPrimary) },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; onNavigateToBotStore() },
                    icon = { Icon(Icons.Default.Store, contentDescription = null, tint = DeepCyan) },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = PitchBlack)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Glass Messenger") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
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
}
