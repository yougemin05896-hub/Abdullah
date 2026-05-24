package com.example.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DataExploration
import androidx.compose.material.icons.filled.NetworkWifi
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.theme.AmoledGray
import com.example.ui.theme.PitchBlack
import com.example.ui.theme.TypographyPrimary
import com.example.ui.theme.NeonCyan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataStorageScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Data and Storage", color = TypographyPrimary) },
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
                "Disk and Network",
                color = NeonCyan,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
            SettingsOption(title = "Storage Usage", subtitle = "2.1 GB used", icon = Icons.Default.Storage)
            SettingsOption(title = "Network Usage", subtitle = "1.5 MB sent, 5 MB received", icon = Icons.Default.NetworkWifi)
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = PitchBlack)
            
            Text(
                "Automatic Media Download",
                color = NeonCyan,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
            SettingsOptionToggle(title = "When using mobile data", initialValue = true)
            SettingsOptionToggle(title = "When connected on Wi-Fi", initialValue = true)
            SettingsOptionToggle(title = "When roaming", initialValue = false)
        }
    }
}
