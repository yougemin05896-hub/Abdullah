package com.example.audio

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VoiceNoteWaveRecorder {
    fun getVisualizerData(): Flow<FloatArray> = flow {
        // Emit live FFT data for voice wave visualization
    }
}
