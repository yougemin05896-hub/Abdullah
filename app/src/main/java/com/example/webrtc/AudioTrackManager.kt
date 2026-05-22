package com.example.webrtc

import org.webrtc.AudioSource
import org.webrtc.AudioTrack
import org.webrtc.MediaConstraints
import org.webrtc.PeerConnectionFactory
import javax.inject.Inject

class AudioTrackManager @Inject constructor() {
    private var audioSource: AudioSource? = null
    private var audioTrack: AudioTrack? = null

    fun createAudioTrack(factory: PeerConnectionFactory): AudioTrack {
        val audioConstraints = MediaConstraints()
        audioSource = factory.createAudioSource(audioConstraints)
        audioTrack = factory.createAudioTrack("ARDAMSa0", audioSource)
        audioTrack?.setEnabled(true)
        return audioTrack!!
    }

    fun setMute(muted: Boolean) {
        audioTrack?.setEnabled(!muted)
    }

    fun release() {
        audioTrack?.dispose()
        audioSource?.dispose()
    }
}
