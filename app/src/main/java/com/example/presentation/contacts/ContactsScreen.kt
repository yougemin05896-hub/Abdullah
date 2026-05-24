package com.example.presentation.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contacts", color = TypographyPrimary) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TypographyPrimary)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = TypographyPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PitchBlack)
            )
        },
        containerColor = AmoledGray
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(15) { index ->
                ContactItem(name = "Commander Squad $index", status = if (index % 2 == 0) "online" else "last seen recently")
            }
        }
    }
}

@Composable
fun ContactItem(name: String, status: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(50.dp).clip(CircleShape).background(TelegramBlue), contentAlignment = Alignment.Center) {
            Icon(Icons.Default.Person, contentDescription = null, tint = TypographyPrimary)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(name, color = TypographyPrimary, style = MaterialTheme.typography.bodyLarge)
            Text(status, color = if (status == "online") NeonCyan else TypographySecondary, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
