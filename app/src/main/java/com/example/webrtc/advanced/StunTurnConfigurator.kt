package com.example.webrtc.advanced

class StunTurnConfigurator {
    fun fetchIceServers(): List<String> {
        // Dynamic fetch of nearest EDGE ICE servers
        return listOf("stun:stun.l.google.com:19302")
    }
}
