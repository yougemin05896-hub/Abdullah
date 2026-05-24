package com.example.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DataUsage
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToPrivacy: () -> Unit,
    onNavigateToData: () -> Unit,
    onNavigateToChatSettings: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings", color = TypographyPrimary) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TypographyPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PitchBlack)
            )
        },
        containerColor = AmoledGray
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            item { 
                Box(modifier = Modifier.clickable { }) {
                    SettingsOption(icon = Icons.Default.Notifications, title = "Notifications and Sounds") 
                }
            }
            item { 
                Box(modifier = Modifier.clickable { onNavigateToPrivacy() }) {
                    SettingsOption(icon = Icons.Default.Lock, title = "Privacy and Security") 
                }
            }
            item { 
                Box(modifier = Modifier.clickable { onNavigateToData() }) {
                    SettingsOption(icon = Icons.Default.DataUsage, title = "Data and Storage") 
                }
            }
            item { 
                Box(modifier = Modifier.clickable { onNavigateToChatSettings() }) {
                    SettingsOption(icon = Icons.Default.Wallpaper, title = "Chat Settings") 
                }
            }
        }
    }
}
