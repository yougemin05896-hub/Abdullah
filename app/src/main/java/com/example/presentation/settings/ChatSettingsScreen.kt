package com.example.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.theme.AmoledGray
import com.example.ui.theme.PitchBlack
import com.example.ui.theme.TypographyPrimary
import com.example.ui.theme.NeonCyan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatSettingsScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat Settings", color = TypographyPrimary) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text(
                "App Theme & Wallpaper",
                color = NeonCyan,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
            SettingsOption(title = "Change Chat Wallpaper", icon = Icons.Default.Wallpaper)
            SettingsOption(title = "Dark Theme", subtitle = "System default", icon = Icons.Default.WbSunny)
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = PitchBlack)
            
            Text(
                "Text Size",
                color = NeonCyan,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
            SettingsOption(title = "Message Text Size", subtitle = "16", icon = Icons.Default.FormatSize)
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = PitchBlack)
            
            SettingsOptionToggle(title = "Save to Gallery", initialValue = false)
            SettingsOptionToggle(title = "In-App Browser", initialValue = true)
        }
    }
}
