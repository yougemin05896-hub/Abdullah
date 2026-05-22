package com.example.data.repository

import com.example.data.local.dao.ChatDao
import com.example.data.local.dao.MessageDao
import com.example.data.local.entities.ChatEntity
import com.example.data.local.entities.MessageEntity
import com.example.data.local.entities.MessageStatus
import com.example.data.network.WebSocketManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepositoryImpl @Inject constructor(
    private val chatDao: ChatDao,
    private val messageDao: MessageDao,
    private val webSocketManager: WebSocketManager
) {
    init {
        // Intercept websocket messages and save to Room (SSOT)
        CoroutineScope(Dispatchers.IO).launch {
            webSocketManager.incomingMessages.collect { netMsg ->
                val entity = MessageEntity(
                    id = netMsg.id,
                    chatId = netMsg.chatId,
                    text = netMsg.text,
                    timestamp = netMsg.timestamp,
                    isOutgoing = false,
                    status = MessageStatus.DELIVERED
                )
                messageDao.insertMessage(entity)
                
                // Update chat last message
                val chat = chatDao.getChatById(netMsg.chatId).firstOrNull()
                if (chat != null) {
                   chatDao.insertChat(chat.copy(lastMessage = netMsg.text, timestamp = netMsg.timestamp, unreadCount = chat.unreadCount + 1))
                } else {
                   chatDao.insertChat(ChatEntity(netMsg.chatId, "Unknown Chat", null, netMsg.text, netMsg.timestamp, 1, false, false))
                }
            }
        }
    }

    fun getChats(): Flow<List<ChatEntity>> = chatDao.getAllChats()

    fun getMessages(chatId: String): Flow<List<MessageEntity>> = messageDao.getMessagesForChat(chatId)

    suspend fun sendMessage(chatId: String, text: String) {
        val tempId = java.util.UUID.randomUUID().toString()
        val msg = MessageEntity(
            id = tempId,
            chatId = chatId,
            text = text,
            timestamp = System.currentTimeMillis(),
            isOutgoing = true,
            status = MessageStatus.SENDING
        )
        // Save to DB immediately (optimistic UI)
        messageDao.insertMessage(msg)
        
        // Ensure chat exists
        val chat = chatDao.getChatById(chatId).firstOrNull()
        if (chat != null) {
            chatDao.insertChat(chat.copy(lastMessage = text, timestamp = System.currentTimeMillis()))
        }
        
        // Send to network
        webSocketManager.sendMessage(chatId, text)
        
        // Update status
        messageDao.insertMessage(msg.copy(status = MessageStatus.SENT))
    }
}
