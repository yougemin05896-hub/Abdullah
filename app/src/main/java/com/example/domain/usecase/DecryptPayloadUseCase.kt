package com.example.domain.usecase

import com.example.data.crypto.SignalProtocolManager
import javax.inject.Inject

class DecryptPayloadUseCase @Inject constructor(
    private val cryptoManager: SignalProtocolManager
) {
    operator fun invoke(payload: String): String {
        return cryptoManager.decrypt(payload)
    }
}
