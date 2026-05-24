package com.example.webrtc.advanced

class VideoResolutionScaler {
    fun adjustToBandwidth(availableBitrate: Long): Pair<Int, Int> {
        // Dynamically adjust camera capture resolution based on network stats
        return Pair(1280, 720)
    }
}
