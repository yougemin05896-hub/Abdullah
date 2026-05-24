package com.example

import android.app.Application
import com.example.di.DependencyProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class XGenamApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DependencyProvider.initialize(this)
        
        // Start connection
        CoroutineScope(Dispatchers.IO).launch {
            DependencyProvider.webSocketManager.connect()
        }
    }
}
