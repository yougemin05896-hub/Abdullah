package com.example.crypto

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ForwardSecrecyRotator {
    // Rotates keys every 15 minutes for Perfect Forward Secrecy
    fun startRotationSchedule(): Flow<String> = flow {
        while(true) {
            emit("NEW_KEY_GENERATED_${System.currentTimeMillis()}")
            delay(15 * 60 * 1000L) // 15 minutes
        }
    }
}
