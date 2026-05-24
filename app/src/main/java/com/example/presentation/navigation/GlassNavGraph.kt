package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.chat.ChatScreen
import com.example.presentation.chat.SavedMessagesScreen
import com.example.presentation.chat.ActiveCallScreen
import com.example.presentation.inbox.InboxScreen
import com.example.presentation.settings.SettingsScreen
import com.example.presentation.settings.PrivacySecurityScreen
import com.example.presentation.settings.DataStorageScreen
import com.example.presentation.settings.ChatSettingsScreen
import com.example.presentation.contacts.ContactsScreen
import com.example.presentation.folders.FoldersScreen
import com.example.presentation.bot_store.BotStoreScreen
import com.example.presentation.profile.UserProfileScreen

@Composable
fun GlassNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "inbox") {
        composable("inbox") {
            InboxScreen(
                onNavigateToChat = { chatId -> navController.navigate("chat/$chatId") },
                onNavigateToSettings = { navController.navigate("settings_main") },
                onNavigateToContacts = { navController.navigate("contacts") },
                onNavigateToSaved = { navController.navigate("saved_messages") }
            )
        }
        composable("chat/{chatId}") { backStackEntry ->
            val chatId = backStackEntry.arguments?.getString("chatId") ?: return@composable
            ChatScreen(
                chatId = chatId,
                onNavigateBack = { navController.navigateUp() },
                onNavigateToProfile = { navController.navigate("profile/$chatId") },
                onNavigateToCall = { navController.navigate("active_call") }
            )
        }
        composable("active_call") {
            ActiveCallScreen(onEndCall = { navController.navigateUp() })
        }
        composable("saved_messages") {
            SavedMessagesScreen(onNavigateBack = { navController.navigateUp() })
        }
        composable("profile/{userId}") {
            UserProfileScreen(onNavigateBack = { navController.navigateUp() })
        }
        composable("settings_main") {
            SettingsScreen(
                onNavigateBack = { navController.navigateUp() },
                onNavigateToPrivacy = { navController.navigate("settings_privacy") },
                onNavigateToData = { navController.navigate("settings_data") },
                onNavigateToChatSettings = { navController.navigate("settings_chat") }
            )
        }
        composable("settings_privacy") {
            PrivacySecurityScreen(onNavigateBack = { navController.navigateUp() })
        }
        composable("settings_data") {
            DataStorageScreen(onNavigateBack = { navController.navigateUp() })
        }
        composable("settings_chat") {
            ChatSettingsScreen(onNavigateBack = { navController.navigateUp() })
        }
        composable("contacts") {
            ContactsScreen(onNavigateBack = { navController.navigateUp() })
        }
    }
}
