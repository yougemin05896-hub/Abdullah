package com.example.data.network

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.serialization.Serializable

// Domain Models for Network
@Serializable
data class NetworkMessage(
    val id: String,
    val chatId: String,
    val text: String,
    val timestamp: Long
)

// Fake WebSocket Manager to simulate network activity
class WebSocketManager {
    
    private val _incomingMessages = MutableSharedFlow<NetworkMessage>()
    val incomingMessages: SharedFlow<NetworkMessage> = _incomingMessages

    private val _connectionState = MutableSharedFlow<Boolean>()
    val connectionState: SharedFlow<Boolean> = _connectionState

    suspend fun connect() {
        _connectionState.emit(true)
        // Simulate some fake initial messages from a bot
        kotlinx.coroutines.delay(2000)
    }

    suspend fun sendMessage(chatId: String, text: String) {
        // Mock echoing back the message from a bot after a delay
        kotlinx.coroutines.delay(1000)
        _incomingMessages.emit(
            NetworkMessage(
                id = java.util.UUID.randomUUID().toString(),
                chatId = chatId,
                text = "GlassBot Reply: I received \"$text\"",
                timestamp = System.currentTimeMillis()
            )
        )
    }

    suspend fun disconnect() {
        _connectionState.emit(false)
    }
}
