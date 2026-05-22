package com.example.domain.usecase

import com.example.data.crypto.SignalProtocolManager
import com.example.data.repository.MessageRepositoryImpl
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: MessageRepositoryImpl,
    private val cryptoManager: SignalProtocolManager
) {
    suspend operator fun invoke(chatId: String, text: String, isEncrypted: Boolean = true) {
        val payload = if (isEncrypted) cryptoManager.encrypt(text) else text
        repository.sendMessage(chatId, payload)
    }
}
