package com.example.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.call.ActiveCallScreen
import com.example.presentation.chat.ChatScreen
import com.example.presentation.inbox.InboxScreen
import com.example.presentation.settings.SettingsScreen
import com.example.presentation.folders.FoldersScreen
import com.example.presentation.bot_store.BotStoreScreen

@Composable
fun GlassNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "inbox",
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut() }
    ) {
        composable("inbox") {
            InboxScreen(
                onNavigateToChat = { chatId -> navController.navigate("chat/$chatId") },
                onNavigateToSettings = { navController.navigate("settings") },
                onNavigateToFolders = { navController.navigate("folders") },
                onNavigateToBotStore = { navController.navigate("bot_store") }
            )
        }
        composable("chat/{chatId}") {
            ChatScreen(
                onNavigateBack = { navController.navigateUp() },
                onNavigateToCall = { navController.navigate("call") }
            )
        }
        composable("call") {
            ActiveCallScreen(
                onEndCall = { navController.navigateUp() }
            )
        }
        composable("settings") {
            SettingsScreen(
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable("folders") {
            FoldersScreen(
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable("bot_store") {
            BotStoreScreen(
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}
