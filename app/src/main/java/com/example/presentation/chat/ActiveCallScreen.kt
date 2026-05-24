package com.example.presentation.chat

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.PitchBlack
import com.example.ui.theme.TelegramBlue
import com.example.ui.theme.TypographyPrimary

@Composable
fun ActiveCallScreen(onEndCall: () -> Unit) {
    var isMuted by remember { mutableStateOf(false) }
    
    val pulseModifier = rememberInfiniteTransition(label = "pulse")
    val pulseAlpha by pulseModifier.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "pulseAlpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PitchBlack),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                // Pulse Rings
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .clip(CircleShape)
                        .background(TelegramBlue.copy(alpha = pulseAlpha))
                )
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(TelegramBlue.copy(alpha = pulseAlpha + 0.2f))
                )
                // Center
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(TelegramBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Text("CX", style = MaterialTheme.typography.displayMedium, color = PitchBlack)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text("Commander X", style = MaterialTheme.typography.headlineMedium, color = TypographyPrimary)
            Text("00:15", style = MaterialTheme.typography.titleMedium, color = NeonCyan)
            
            Spacer(modifier = Modifier.height(48.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CallAction(
                    icon = Icons.Default.VolumeUp,
                    isActive = false,
                    onClick = {}
                )
                
                CallAction(
                    icon = if (isMuted) Icons.Default.MicOff else Icons.Default.Mic,
                    isActive = isMuted,
                    onClick = { isMuted = !isMuted }
                )
                
                FloatingActionButton(
                    onClick = onEndCall,
                    containerColor = Color.Red,
                    shape = CircleShape,
                    modifier = Modifier.size(72.dp)
                ) {
                    Icon(Icons.Default.CallEnd, contentDescription = "End Call", tint = Color.White, modifier = Modifier.size(32.dp))
                }
            }
        }
    }
}

@Composable
fun CallAction(icon: androidx.compose.ui.graphics.vector.ImageVector, isActive: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(if (isActive) Color.White else Color(0xFF222222))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(icon, contentDescription = null, tint = if (isActive) PitchBlack else TypographyPrimary, modifier = Modifier.size(32.dp))
        }
    }
}
