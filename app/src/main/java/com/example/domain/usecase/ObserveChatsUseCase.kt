package com.example.domain.usecase

import com.example.data.local.entities.ChatEntity
import com.example.data.repository.MessageRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveChatsUseCase @Inject constructor(
    private val repository: MessageRepositoryImpl
) {
    operator fun invoke(): Flow<List<ChatEntity>> {
        return repository.getChats()
    }
}
