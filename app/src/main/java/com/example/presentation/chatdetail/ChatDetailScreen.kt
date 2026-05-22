package com.example.presentation.chatdetail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.entities.MessageEntity
import com.example.ui.components.liquidGlass
import com.example.ui.theme.DeepCyan
import com.example.ui.theme.PitchBlack

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ChatDetailScreen(
    chatId: String,
    viewModel: ChatDetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ChatDetailViewModel.provideFactory(chatId)),
    onNavigateBack: () -> Unit
) {
    val messages by viewModel.messages.collectAsStateWithLifecycle()
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PitchBlack.copy(alpha = 0.5f),
                    titleContentColor = DeepCyan,
                    navigationIconContentColor = DeepCyan
                ),
                modifier = Modifier.liquidGlass(
                    cornerRadius = 0.dp,
                    fillColor = PitchBlack.copy(alpha = 0.3f),
                    borderWidth = 0.dp
                )
            )
        },
        containerColor = PitchBlack,
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .liquidGlass(cornerRadius = 24.dp, fillColor = Color(0x1AFFFFFF), borderWidth = 1.dp)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* attachment */ }) {
                    Icon(Icons.Default.Add, contentDescription = "Attach", tint = DeepCyan)
                }
                TextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text("Message...", color = Color.Gray) }
                )
                
                IconButton(
                    onClick = {
                        if (inputText.isNotBlank()) {
                            viewModel.processIntent(ChatDetailIntent.SendMessage(inputText))
                            inputText = ""
                        }
                    }
                ) {
                    AnimatedContent(targetState = inputText.isNotBlank(), label = "SendIcon") { showSend ->
                        if (showSend) {
                            Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send", tint = DeepCyan)
                        } else {
                            Icon(Icons.Default.Add, contentDescription = "Mic", tint = DeepCyan)
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            reverseLayout = false // In a real app we'd reverse it for newest at bottom
        ) {
            items(messages) { msg ->
                MessageBubble(message = msg)
            }
        }
    }
}

@Composable
fun MessageBubble(message: MessageEntity) {
    val alignment = if (message.isOutgoing) Alignment.CenterEnd else Alignment.CenterStart
    val bubbleColor = if (message.isOutgoing) DeepCyan.copy(alpha = 0.2f) else Color(0x33FFFFFF)
    
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .liquidGlass(
                    cornerRadius = 16.dp,
                    fillColor = bubbleColor,
                    borderWidth = 0.5.dp
                )
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text(
                text = message.text,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}
