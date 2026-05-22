package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.ChatDao
import com.example.data.local.dao.FolderDao
import com.example.data.local.dao.MessageDao
import com.example.data.local.entities.ChatEntity
import com.example.data.local.entities.FolderEntity
import com.example.data.local.entities.MessageEntity

@Database(
    entities = [ChatEntity::class, MessageEntity::class, FolderEntity::class],
    version = 2,
    exportSchema = false
)
abstract class GlassDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao
    abstract fun folderDao(): FolderDao
}
