package com.example.domain.usecase

import javax.inject.Inject

class SyncOfflineMessagesUseCase @Inject constructor() {
    suspend operator fun invoke() {
        // Sync pending messages
    }
}
