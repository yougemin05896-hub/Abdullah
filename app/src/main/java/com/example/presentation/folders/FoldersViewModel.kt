package com.example.presentation.folders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.entities.FolderEntity
import com.example.data.repository.ChatRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

sealed class FoldersIntent {
    object LoadFolders : FoldersIntent()
    data class CreateFolder(val name: String, val filterTypes: String) : FoldersIntent()
}

data class FoldersState(
    val folders: List<FolderEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class FoldersViewModel(
    private val chatRepository: ChatRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(FoldersState())
    val state: StateFlow<FoldersState> = _state.asStateFlow()

    init {
        processIntent(FoldersIntent.LoadFolders)
    }

    fun processIntent(intent: FoldersIntent) {
        when (intent) {
            is FoldersIntent.LoadFolders -> loadFolders()
            is FoldersIntent.CreateFolder -> createFolder(intent.name, intent.filterTypes)
        }
    }

    private fun loadFolders() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            chatRepository.getAllFolders()
                .catch { e -> _state.value = _state.value.copy(isLoading = false, error = e.message) }
                .collect { folders ->
                    _state.value = _state.value.copy(folders = folders, isLoading = false, error = null)
                }
        }
    }

    private fun createFolder(name: String, filterTypes: String) {
        viewModelScope.launch {
            chatRepository.createFolder(name, filterTypes, state.value.folders.size)
        }
    }
}
