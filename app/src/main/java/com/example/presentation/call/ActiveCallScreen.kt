package com.example.presentation.call

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.di.DependencyProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.liquidGlass
import com.example.ui.theme.DeepCyan
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.PitchBlack
import com.example.ui.theme.TypographyPrimary

@Composable
fun ActiveCallScreen(
    viewModel: CallViewModel = viewModel(factory = DependencyProvider.factory),
    onEndCall: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    LaunchedEffect(Unit) {
        viewModel.processIntent(CallIntent.StartCall)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PitchBlack)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .liquidGlass(cornerRadius = 75.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("End-to-End Encrypted", color = DeepCyan)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            Text("Active Call", color = TypographyPrimary)
            Spacer(modifier = Modifier.height(64.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = { viewModel.processIntent(CallIntent.ToggleMute) },
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(com.example.ui.theme.GlassDarkGrey)
                ) {
                    Icon(
                        imageVector = if (state.isMuted) Icons.Default.MicOff else Icons.Default.Mic,
                        contentDescription = "Mute",
                        tint = TypographyPrimary
                    )
                }
                
                IconButton(
                    onClick = {
                        viewModel.processIntent(CallIntent.EndCall)
                        onEndCall()
                    },
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(ErrorRed)
                ) {
                    Icon(
                        imageVector = Icons.Default.CallEnd,
                        contentDescription = "End Call",
                        tint = PitchBlack
                    )
                }
            }
        }
    }
}
