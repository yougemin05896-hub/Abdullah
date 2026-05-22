package com.example.presentation.chatdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.local.entities.MessageEntity
import com.example.data.repository.MessageRepositoryImpl
import com.example.di.DependencyProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class ChatDetailIntent {
    data class SendMessage(val text: String) : ChatDetailIntent()
}

class ChatDetailViewModel(
    private val repository: MessageRepositoryImpl,
    private val chatId: String
) : ViewModel() {

    val messages: StateFlow<List<MessageEntity>> = repository.getMessages(chatId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun processIntent(intent: ChatDetailIntent) {
        viewModelScope.launch {
            when (intent) {
                is ChatDetailIntent.SendMessage -> {
                    if (intent.text.isNotBlank()) {
                        repository.sendMessage(chatId, intent.text)
                    }
                }
            }
        }
    }
    
    companion object {
        fun provideFactory(chatId: String): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ChatDetailViewModel(DependencyProvider.messageRepository, chatId) as T
            }
        }
    }
}
