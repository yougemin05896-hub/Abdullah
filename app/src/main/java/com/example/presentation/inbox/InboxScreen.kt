package com.example.presentation.inbox

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.di.DependencyProvider
import com.example.presentation.inbox.components.FolderTabs
import com.example.presentation.inbox.components.GlassDrawer
import com.example.ui.theme.*
import com.example.data.local.entities.ChatEntity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InboxScreen(
    viewModel: InboxViewModel = viewModel(factory = DependencyProvider.factory),
    onNavigateToChat: (String) -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToContacts: () -> Unit,
    onNavigateToSaved: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedTab by remember { mutableIntStateOf(0) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            GlassDrawer(
                onNavigateToSettings = { scope.launch { drawerState.close() }; onNavigateToSettings() },
                onNavigateToContacts = { scope.launch { drawerState.close() }; onNavigateToContacts() },
                onNavigateToSaved = { scope.launch { drawerState.close() }; onNavigateToSaved() }
            )
        }
    ) {
        Scaffold(
            topBar = {
                Column(modifier = Modifier.background(PitchBlack)) {
                    TopAppBar(
                        title = { 
                            Text(
                                text = "XGenam", 
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                            ) 
                        },
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
                            containerColor = PitchBlack,
                            titleContentColor = TypographyPrimary,
                            navigationIconContentColor = TypographyPrimary,
                            actionIconContentColor = TypographyPrimary
                        )
                    )
                    FolderTabs(selectedTabIndex = selectedTab, onTabSelected = { selectedTab = it })
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onNavigateToContacts() },
                    containerColor = TelegramBlue,
                    contentColor = TypographyPrimary,
                    modifier = Modifier.padding(16.dp),
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "New Message")
                }
            },
            containerColor = AmoledGray
        ) { padding ->
            LazyColumn(
                contentPadding = PaddingValues(top = padding.calculateTopPadding(), bottom = 80.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.chats) { chat ->
                    InboxChatItem(chat = chat, onClick = { onNavigateToChat(chat.id) })
                }
                
                // Demo item for empty state visually
                if (state.chats.isEmpty()) {
                    items(5) { index ->
                        InboxChatItem(
                            chat = ChatEntity(
                                id = "preview_$index",
                                name = "Commander Squad $index",
                                isGroup = false,
                                avatarUrl = null,
                                lastMessage = "Operation successful. Proceeding to next phase.",
                                timestamp = System.currentTimeMillis(),
                                unreadCount = 0
                            ),
                            onClick = { onNavigateToChat("preview_$index") }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InboxChatItem(chat: ChatEntity, onClick: () -> Unit) {
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
                .background(TelegramBlue.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = chat.name.take(1).uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = TelegramBlue,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = chat.name,
                    color = TypographyPrimary,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "12:00 PM",
                    color = TypographySecondary,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chat.lastMessage?.takeIf { it.isNotBlank() } ?: "Shared a secure payload...",
                color = TypographySecondary,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
