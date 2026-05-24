package com.example.sync

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SyncStateManager {
    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing

    fun notifySyncStarted() { _isSyncing.value = true }
    fun notifySyncFinished() { _isSyncing.value = false }
}
