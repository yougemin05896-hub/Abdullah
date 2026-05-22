package com.example.data.repository

import com.example.data.local.dao.ChatDao
import com.example.data.local.dao.FolderDao
import com.example.data.local.entities.ChatEntity
import com.example.data.local.entities.FolderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor(
    private val chatDao: ChatDao,
    private val folderDao: FolderDao
) {
    fun getAllChats(): Flow<List<ChatEntity>> = chatDao.getAllChats()

    fun getAllFolders(): Flow<List<FolderEntity>> = folderDao.getAllFolders()

    suspend fun createFolder(name: String, filterTypes: String, orderIndex: Int) {
        val folder = FolderEntity(
            id = java.util.UUID.randomUUID().toString(),
            name = name,
            filterTypes = filterTypes,
            orderIndex = orderIndex
        )
        folderDao.insertFolder(folder)
    }
}
