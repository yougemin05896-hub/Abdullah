package com.example.domain.usecase

import com.example.data.crypto.SignalProtocolManager
import com.example.data.local.entities.MessageEntity
import com.example.data.repository.MessageRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReceiveMessageUseCase @Inject constructor(
    private val repository: MessageRepositoryImpl,
    private val cryptoManager: SignalProtocolManager
) {
    operator fun invoke(chatId: String): Flow<List<MessageEntity>> {
        return repository.getMessages(chatId).map { messages ->
            messages.map { msg ->
                val decryptedText = if (!msg.isOutgoing) cryptoManager.decrypt(msg.text) else msg.text
                msg.copy(text = decryptedText)
            }
        }
    }
}
