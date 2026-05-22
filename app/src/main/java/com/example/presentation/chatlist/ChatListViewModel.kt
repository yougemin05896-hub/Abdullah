package com.example.presentation.chatlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.local.entities.ChatEntity
import com.example.data.repository.MessageRepositoryImpl
import com.example.di.DependencyProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class ChatListIntent {
    data class OnChatClicked(val chatId: String) : ChatListIntent()
    object OnRefresh : ChatListIntent()
}

data class ChatListState(
    val chats: List<ChatEntity> = emptyList(),
    val isLoading: Boolean = false
)

class ChatListViewModel(
    private val repository: MessageRepositoryImpl
) : ViewModel() {

    private val _intents = MutableSharedFlow<ChatListIntent>()
    val intents = _intents.asSharedFlow()

    val state: StateFlow<ChatListState> = repository.getChats()
        .map { chats -> ChatListState(chats = chats) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ChatListState()
        )

    fun processIntent(intent: ChatListIntent) {
        viewModelScope.launch {
            _intents.emit(intent)
        }
    }
    
    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ChatListViewModel(DependencyProvider.messageRepository) as T
            }
        }
    }
}
