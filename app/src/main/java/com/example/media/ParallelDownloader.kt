package com.example.media

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ParallelDownloader {
    fun downloadChunks(fileId: String, totalChunks: Int): Flow<Float> = flow {
        // Stream download progress for Fly.io gigabit networks
        for (i in 1..totalChunks) {
            emit(i.toFloat() / totalChunks.toFloat())
        }
    }
}
