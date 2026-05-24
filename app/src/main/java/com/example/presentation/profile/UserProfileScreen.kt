package com.example.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VideoCall
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TypographyPrimary)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More", tint = TypographyPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PitchBlack)
            )
        },
        containerColor = PitchBlack
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                            .background(TelegramBlue.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("CX", style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold), color = TelegramBlue)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Commander X", style = MaterialTheme.typography.headlineMedium, color = TypographyPrimary)
                    Text("last seen recently", style = MaterialTheme.typography.bodyMedium, color = TypographySecondary)
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
                    ) {
                        ProfileAction(Icons.Default.Call, "Call")
                        ProfileAction(Icons.Default.VideoCall, "Video")
                        ProfileAction(Icons.Default.Search, "Search")
                    }
                    
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
            
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text("Info", color = NeonCyan, style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("+1 555-010-9999", color = TypographyPrimary, style = MaterialTheme.typography.bodyLarge)
                    Text("Mobile", color = TypographySecondary, style = MaterialTheme.typography.labelMedium)
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("@commander_x", color = TypographyPrimary, style = MaterialTheme.typography.bodyLarge)
                    Text("Username", color = TypographySecondary, style = MaterialTheme.typography.labelMedium)
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Cyberpunk protocol initialized. Awaiting secure handshake.", color = TypographyPrimary, style = MaterialTheme.typography.bodyLarge)
                    Text("Bio", color = TypographySecondary, style = MaterialTheme.typography.labelMedium)
                }
            }
            
            item {
                HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp), color = AmoledGray)
            }
            
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Notifications", color = TypographyPrimary, style = MaterialTheme.typography.bodyLarge)
                    Switch(
                        checked = true, 
                        onCheckedChange = {},
                        colors = SwitchDefaults.colors(checkedThumbColor = NeonCyan, checkedTrackColor = NeonCyan.copy(alpha = 0.5f))
                    )
                }
            }
            
            item {
                HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp), color = AmoledGray)
            }
            
            item {
                Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                    Text("Shared Media", color = NeonCyan, style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    // Dummy row of photos
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        repeat(4) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .background(AmoledGray)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileAction(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(AmoledGray),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = label, tint = TelegramBlue)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, color = TypographyPrimary, style = MaterialTheme.typography.bodySmall)
    }
}
