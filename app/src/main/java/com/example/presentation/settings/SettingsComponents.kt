package com.example.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.theme.TypographyPrimary
import com.example.ui.theme.TypographySecondary
import com.example.ui.theme.NeonCyan
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun SettingsOption(title: String, subtitle: String? = null, icon: ImageVector? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, tint = TypographySecondary, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(32.dp))
        }
        Column {
            Text(title, color = TypographyPrimary, style = MaterialTheme.typography.bodyLarge)
            if (subtitle != null) {
                Text(subtitle, color = TypographySecondary, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun SettingsOptionToggle(title: String, initialValue: Boolean) {
    var checked by remember { mutableStateOf(initialValue) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, color = TypographyPrimary, style = MaterialTheme.typography.bodyLarge)
        Switch(
            checked = checked,
            onCheckedChange = { checked = it },
            colors = SwitchDefaults.colors(checkedThumbColor = NeonCyan, checkedTrackColor = NeonCyan.copy(alpha = 0.5f))
        )
    }
}
