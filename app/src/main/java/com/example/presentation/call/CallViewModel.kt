package com.example.presentation.call

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webrtc.WebRtcClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class CallIntent {
    object StartCall : CallIntent()
    object EndCall : CallIntent()
    object ToggleMute : CallIntent()
}

data class CallState(
    val isCalling: Boolean = false,
    val isMuted: Boolean = false
)

class CallViewModel(
    private val webRtcClient: WebRtcClient
) : ViewModel() {

    private val _state = MutableStateFlow(CallState())
    val state: StateFlow<CallState> = _state.asStateFlow()

    fun processIntent(intent: CallIntent) {
        when (intent) {
            is CallIntent.StartCall -> {
                webRtcClient.startCall()
                _state.value = _state.value.copy(isCalling = true)
            }
            is CallIntent.EndCall -> {
                webRtcClient.stopCall()
                _state.value = _state.value.copy(isCalling = false)
            }
            is CallIntent.ToggleMute -> {
                val newMute = !state.value.isMuted
                // Assume audio track manager access is mediated via WebRtcClient in real app
                _state.value = _state.value.copy(isMuted = newMute)
            }
        }
    }
}
