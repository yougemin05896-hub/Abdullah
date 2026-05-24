package com.example.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.Phishing
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.VisibilityOff
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
fun PrivacySecurityScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Privacy and Security", color = TypographyPrimary) },
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
                "Privacy",
                color = NeonCyan,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
            SettingsOption(title = "Phone Number", subtitle = "Nobody", icon = Icons.Default.VisibilityOff)
            SettingsOption(title = "Last Seen & Online", subtitle = "My Contacts", icon = Icons.Default.LockClock)
            SettingsOption(title = "Profile Photos", subtitle = "Everyone", icon = Icons.Default.Security)
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = PitchBlack)
            
            Text(
                "Security",
                color = NeonCyan,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
            SettingsOption(title = "Passcode Lock", subtitle = "Off", icon = Icons.Default.LockClock)
            SettingsOption(title = "Two-Step Verification", subtitle = "On", icon = Icons.Default.Security)
            SettingsOption(title = "Active Sessions", subtitle = "1 session", icon = Icons.Default.Phishing)
        }
    }
}
