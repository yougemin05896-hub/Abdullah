package com.example.presentation.inbox.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.PitchBlack
import com.example.ui.theme.TelegramBlue
import com.example.ui.theme.TypographyPrimary
import com.example.ui.theme.TypographySecondary

@Composable
fun GlassDrawer(
    onNavigateToSettings: () -> Unit,
    onNavigateToContacts: () -> Unit,
    onNavigateToSaved: () -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = PitchBlack,
        drawerContentColor = TypographyPrimary,
        modifier = Modifier.width(320.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF161616))
                .padding(top = 40.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(TelegramBlue.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "XG",
                            style = MaterialTheme.typography.headlineMedium,
                            color = TelegramBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    IconButton(onClick = { /* Toggle Theme */ }) {
                        Icon(Icons.Default.Brightness4, contentDescription = "Theme", tint = TypographyPrimary)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("XGenam Commander", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = TypographyPrimary)
                Text("+1 (555) 010-9999", style = MaterialTheme.typography.bodyMedium, color = TypographySecondary)
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        DrawerItem("New Group", Icons.Default.Group) {}
        DrawerItem("New Secret Chat", Icons.Default.Lock) {}
        DrawerItem("New Channel", Icons.Default.Campaign) {}
        
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFF222222))
        
        DrawerItem("Contacts", Icons.Default.PersonSearch) { onNavigateToContacts() }
        DrawerItem("Calls", Icons.Default.Call) {}
        DrawerItem("Saved Messages", Icons.Default.Bookmark) { onNavigateToSaved() }
        DrawerItem("Settings", Icons.Default.Settings) { onNavigateToSettings() }
        
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFF222222))
        DrawerItem("Invite Friends", Icons.Default.PersonAdd) {}
        DrawerItem("XGenam Features", Icons.Default.Help) {}
    }
}

@Composable
private fun DrawerItem(text: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = text, tint = TypographySecondary, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(32.dp))
        Text(text, color = TypographyPrimary, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
    }
}
