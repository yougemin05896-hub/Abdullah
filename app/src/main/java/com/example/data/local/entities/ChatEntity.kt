package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "chats")
@Serializable
data class ChatEntity(
    @PrimaryKey val id: String,
    val name: String,
    val avatarUrl: String?,
    val lastMessage: String?,
    val timestamp: Long,
    val unreadCount: Int,
    val isGroup: Boolean = false,
    val isBot: Boolean = false
)
