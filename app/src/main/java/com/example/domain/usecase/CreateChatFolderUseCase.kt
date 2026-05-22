package com.example.domain.usecase

import com.example.data.local.dao.FolderDao
import com.example.data.local.entities.FolderEntity
import java.util.UUID
import javax.inject.Inject

class CreateChatFolderUseCase @Inject constructor(
    private val folderDao: FolderDao
) {
    suspend operator fun invoke(name: String, chatIds: List<String>) {
        val folder = FolderEntity(
            id = UUID.randomUUID().toString(),
            name = name,
            filterTypes = "", // Adjust this as needed based on logic
            orderIndex = 0
        )
        folderDao.insertFolder(folder)
    }
}
