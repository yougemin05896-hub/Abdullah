package com.example.presentation.chat

// Removed Hilt imports
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.entities.MessageEntity
import com.example.domain.usecase.SendMessageUseCase
import com.example.data.repository.MessageRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

sealed class ChatIntent {
    data class LoadMessages(val chatId: String) : ChatIntent()
    data class SendMessage(val chatId: String, val text: String, val isEncrypted: Boolean) : ChatIntent()
}

data class ChatState(
    val messages: List<MessageEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ChatViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val messageRepository: MessageRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ChatState())
    val state: StateFlow<ChatState> = _state.asStateFlow()

    init {
        val chatId = savedStateHandle.get<String>("chatId") ?: ""
        if (chatId.isNotBlank()) {
            processIntent(ChatIntent.LoadMessages(chatId))
        }
    }

    fun processIntent(intent: ChatIntent) {
        when (intent) {
            is ChatIntent.LoadMessages -> loadMessages(intent.chatId)
            is ChatIntent.SendMessage -> sendMessage(intent.chatId, intent.text, intent.isEncrypted)
        }
    }

    private fun loadMessages(chatId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            messageRepository.getMessages(chatId)
                .catch { e -> _state.value = _state.value.copy(isLoading = false, error = e.message) }
                .collect { messages ->
                    _state.value = _state.value.copy(messages = messages, isLoading = false, error = null)
                }
        }
    }

    private fun sendMessage(chatId: String, text: String, isEncrypted: Boolean) {
        viewModelScope.launch {
            sendMessageUseCase(chatId, text, isEncrypted)
        }
    }
}
