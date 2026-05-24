package com.example.presentation.chat.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttachmentBottomSheet(
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = AmoledGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AttachmentOption(Icons.Default.Image, "Gallery")
                AttachmentOption(Icons.Default.InsertDriveFile, "File")
                AttachmentOption(Icons.Default.LocationOn, "Location")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AttachmentOption(Icons.Default.ContactPage, "Contact")
                AttachmentOption(Icons.Default.Poll, "Poll")
                Spacer(modifier = Modifier.width(64.dp)) 
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun AttachmentOption(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(64.dp),
            colors = IconButtonDefaults.iconButtonColors(containerColor = AmoledLightGray)
        ) {
            Icon(icon, contentDescription = label, tint = TelegramBlue, modifier = Modifier.size(32.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, color = TypographyPrimary, style = MaterialTheme.typography.bodyMedium)
    }
}
