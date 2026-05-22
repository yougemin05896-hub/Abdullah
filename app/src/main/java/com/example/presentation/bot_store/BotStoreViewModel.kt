package com.example.presentation.bot_store

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class BotStoreState(
    val bots: List<String> = listOf("Translation Bot", "Image Gen Bot", "Weather Bot")
)

class BotStoreViewModel() : ViewModel() {
    private val _state = MutableStateFlow(BotStoreState())
    val state: StateFlow<BotStoreState> = _state.asStateFlow()
}
