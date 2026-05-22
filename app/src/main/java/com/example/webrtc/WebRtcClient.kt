package com.example.webrtc

import android.content.Context
import org.webrtc.PeerConnectionFactory
import org.webrtc.PeerConnection
import org.webrtc.IceCandidate
import org.webrtc.SessionDescription
import javax.inject.Inject

class WebRtcClient @Inject constructor(
    private val context: Context,
    private val peerConnectionObserver: PeerConnectionObserver,
    private val audioTrackManager: AudioTrackManager
) {
    private var peerConnectionFactory: PeerConnectionFactory? = null
    private var peerConnection: PeerConnection? = null

    init {
        // Initialize WebRTC and PeerConnectionFactory
        PeerConnectionFactory.initialize(
            PeerConnectionFactory.InitializationOptions.builder(context)
                .setEnableInternalTracer(true)
                .createInitializationOptions()
        )
        
        peerConnectionFactory = PeerConnectionFactory.builder().createPeerConnectionFactory()
    }

    fun startCall() {
        // Create PeerConnection
        val rtcConfig = PeerConnection.RTCConfiguration(emptyList())
        peerConnection = peerConnectionFactory?.createPeerConnection(rtcConfig, peerConnectionObserver)
        
        // Add Audio Track
        val audioTrack = audioTrackManager.createAudioTrack(peerConnectionFactory!!)
        peerConnection?.addTrack(audioTrack)
        
        // Create Offer
        peerConnection?.createOffer(peerConnectionObserver, org.webrtc.MediaConstraints())
    }

    fun stopCall() {
        peerConnection?.close()
        peerConnection = null
    }
}
