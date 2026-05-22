package com.example.domain.usecase

import javax.inject.Inject

class InitiateCallUseCase @Inject constructor() {
    suspend operator fun invoke(chatId: String) {
        // WebRTC SDP offer generation
    }
}
