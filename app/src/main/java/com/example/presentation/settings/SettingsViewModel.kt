package com.example.presentation.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SettingsState(
    val isE2eeEnabled: Boolean = true,
    val dataSaverEnabled: Boolean = false
)

class SettingsViewModel() : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    fun toggleE2ee() {
        _state.value = _state.value.copy(isE2eeEnabled = !_state.value.isE2eeEnabled)
    }
}
