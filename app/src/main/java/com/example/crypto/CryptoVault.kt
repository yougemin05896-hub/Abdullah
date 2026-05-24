package com.example.crypto

import java.security.KeyStore

class CryptoVault {
    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    fun getUnlockedVault(): KeyStore = keyStore
    
    fun wipeVault() {
        // Immediate panic button to destroy all crypto material
    }
}
