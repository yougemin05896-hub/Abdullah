package com.example.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.audio.MediaAudioEngine
import com.example.data.crypto.KeyStoreHelper
import com.example.data.crypto.SignalProtocolManager
import com.example.data.local.GlassDatabase
import com.example.data.local.dao.ChatDao
import com.example.data.local.dao.FolderDao
import com.example.data.local.dao.MessageDao
import com.example.data.network.BotApiManager
import com.example.data.network.WebSocketManager
import com.example.data.repository.MessageRepositoryImpl
import com.example.domain.usecase.AcceptCallUseCase
import com.example.domain.usecase.CompressMediaUseCase
import com.example.domain.usecase.CreateChatFolderUseCase
import com.example.domain.usecase.DecryptPayloadUseCase
import com.example.domain.usecase.EncryptPayloadUseCase
import com.example.domain.usecase.InitiateCallUseCase
import com.example.domain.usecase.ObserveChatsUseCase
import com.example.domain.usecase.ReceiveMessageUseCase
import com.example.domain.usecase.SendMessageUseCase
import com.example.domain.usecase.SyncOfflineMessagesUseCase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Manual DI replacing Hilt due to AGP/KSP container compatibility issues
object DependencyProvider {
    lateinit var context: Context
        private set
    lateinit var database: GlassDatabase
        private set
    lateinit var chatDao: ChatDao
        private set
    lateinit var messageDao: MessageDao
        private set
    lateinit var folderDao: FolderDao
        private set
    lateinit var webSocketManager: WebSocketManager
        private set
    lateinit var botApiManager: BotApiManager
        private set
    lateinit var keyStoreHelper: KeyStoreHelper
        private set
    lateinit var signalProtocolManager: SignalProtocolManager
        private set
    lateinit var messageRepository: MessageRepositoryImpl
        private set
    lateinit var mediaAudioEngine: MediaAudioEngine
        private set
    lateinit var chatRepository: com.example.data.repository.ChatRepositoryImpl
        private set
    lateinit var webRtcClient: com.example.webrtc.WebRtcClient
        private set

    // Use cases
    lateinit var sendMessageUseCase: SendMessageUseCase
        private set
    lateinit var receiveMessageUseCase: ReceiveMessageUseCase
        private set
    lateinit var observeChatsUseCase: ObserveChatsUseCase
        private set
    lateinit var createChatFolderUseCase: CreateChatFolderUseCase
        private set
    lateinit var encryptPayloadUseCase: EncryptPayloadUseCase
        private set
    lateinit var decryptPayloadUseCase: DecryptPayloadUseCase
        private set
    lateinit var compressMediaUseCase: CompressMediaUseCase
        private set
    lateinit var syncOfflineMessagesUseCase: SyncOfflineMessagesUseCase
        private set
    lateinit var initiateCallUseCase: InitiateCallUseCase
        private set
    lateinit var acceptCallUseCase: AcceptCallUseCase
        private set

    // New Advanced Modules
    lateinit var advancedKeyManager: com.example.crypto.AdvancedKeyManager
        private set
    lateinit var cryptoVault: com.example.crypto.CryptoVault
        private set
    lateinit var biometricAuthManager: com.example.crypto.BiometricAuthManager
        private set
    lateinit var sqlCipherConfig: com.example.crypto.SqlCipherConfig
        private set
    lateinit var syncStateManager: com.example.sync.SyncStateManager
        private set
    lateinit var geminiBotOrchestrator: com.example.ai.GeminiBotOrchestrator
        private set

    fun initialize(application: Application) {
        context = application.applicationContext
        
        // Database
        database = Room.databaseBuilder(
            context,
            GlassDatabase::class.java,
            "glass_messenger.db"
        ).fallbackToDestructiveMigration().build()
        chatDao = database.chatDao()
        messageDao = database.messageDao()
        folderDao = database.folderDao()
        
        // Network
        webSocketManager = WebSocketManager()
        botApiManager = BotApiManager()
        
        // Crypto
        keyStoreHelper = KeyStoreHelper()
        signalProtocolManager = SignalProtocolManager(keyStoreHelper)
        
        // Repository
        messageRepository = MessageRepositoryImpl(chatDao, messageDao, webSocketManager)
        chatRepository = com.example.data.repository.ChatRepositoryImpl(chatDao, folderDao)
        
        // Audio
        mediaAudioEngine = MediaAudioEngine(context)
        
        // WebRTC
        val peerConnectionObserver = com.example.webrtc.PeerConnectionObserver()
        val audioTrackManager = com.example.webrtc.AudioTrackManager()
        webRtcClient = com.example.webrtc.WebRtcClient(context, peerConnectionObserver, audioTrackManager)

        // Use cases
        sendMessageUseCase = SendMessageUseCase(messageRepository, signalProtocolManager)
        receiveMessageUseCase = ReceiveMessageUseCase(messageRepository, signalProtocolManager)
        observeChatsUseCase = ObserveChatsUseCase(messageRepository)
        createChatFolderUseCase = CreateChatFolderUseCase(folderDao)
        encryptPayloadUseCase = EncryptPayloadUseCase(signalProtocolManager)
        decryptPayloadUseCase = DecryptPayloadUseCase(signalProtocolManager)
        compressMediaUseCase = CompressMediaUseCase()
        syncOfflineMessagesUseCase = SyncOfflineMessagesUseCase()
        initiateCallUseCase = InitiateCallUseCase()
        acceptCallUseCase = AcceptCallUseCase()

        // Initialize Advanced Modules
        advancedKeyManager = com.example.crypto.AdvancedKeyManager()
        cryptoVault = com.example.crypto.CryptoVault()
        biometricAuthManager = com.example.crypto.BiometricAuthManager()
        sqlCipherConfig = com.example.crypto.SqlCipherConfig()
        syncStateManager = com.example.sync.SyncStateManager()
        geminiBotOrchestrator = com.example.ai.GeminiBotOrchestrator()
    }

    val factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return when {
                modelClass.isAssignableFrom(com.example.presentation.inbox.InboxViewModel::class.java) -> com.example.presentation.inbox.InboxViewModel(chatRepository) as T
                modelClass.isAssignableFrom(com.example.presentation.chat.ChatViewModel::class.java) -> com.example.presentation.chat.ChatViewModel(sendMessageUseCase, messageRepository, androidx.lifecycle.SavedStateHandle()) as T
                modelClass.isAssignableFrom(com.example.presentation.call.CallViewModel::class.java) -> com.example.presentation.call.CallViewModel(webRtcClient) as T
                modelClass.isAssignableFrom(com.example.presentation.folders.FoldersViewModel::class.java) -> com.example.presentation.folders.FoldersViewModel(chatRepository) as T
                modelClass.isAssignableFrom(com.example.presentation.settings.SettingsViewModel::class.java) -> com.example.presentation.settings.SettingsViewModel() as T
                modelClass.isAssignableFrom(com.example.presentation.bot_store.BotStoreViewModel::class.java) -> com.example.presentation.bot_store.BotStoreViewModel() as T
                else -> throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}
