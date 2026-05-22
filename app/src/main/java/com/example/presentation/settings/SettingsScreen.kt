package com.example.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.di.DependencyProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.liquidGlass
import com.example.ui.theme.DeepCyan
import com.example.ui.theme.PitchBlack
import com.example.ui.theme.TypographyPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel(factory = DependencyProvider.factory),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PitchBlack.copy(alpha = 0.5f),
                    titleContentColor = DeepCyan,
                    navigationIconContentColor = DeepCyan,
                    actionIconContentColor = DeepCyan
                ),
                modifier = Modifier.liquidGlass(cornerRadius = 0.dp, borderWidth = 0.dp)
            )
        },
        containerColor = PitchBlack
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("End-to-End Encryption", color = TypographyPrimary)
                Switch(
                    checked = state.isE2eeEnabled,
                    onCheckedChange = { viewModel.toggleE2ee() },
                    colors = SwitchDefaults.colors(checkedThumbColor = DeepCyan)
                )
            }
        }
    }
}
