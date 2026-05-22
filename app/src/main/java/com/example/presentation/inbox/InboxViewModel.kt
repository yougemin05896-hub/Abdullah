package com.example.presentation.inbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.entities.ChatEntity
import com.example.data.repository.ChatRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

sealed class InboxIntent {
    object LoadChats : InboxIntent()
    data class SelectFolder(val folderId: String) : InboxIntent()
}

data class InboxState(
    val chats: List<ChatEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class InboxViewModel(
    private val chatRepository: ChatRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(InboxState())
    val state: StateFlow<InboxState> = _state.asStateFlow()

    init {
        processIntent(InboxIntent.LoadChats)
    }

    fun processIntent(intent: InboxIntent) {
        when (intent) {
            is InboxIntent.LoadChats -> loadChats()
            is InboxIntent.SelectFolder -> { /* Filter logic */ }
        }
    }

    private fun loadChats() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            chatRepository.getAllChats()
                .catch { e -> _state.value = _state.value.copy(isLoading = false, error = e.message) }
                .collect { chats ->
                    _state.value = _state.value.copy(chats = chats, isLoading = false, error = null)
                }
        }
    }
}
