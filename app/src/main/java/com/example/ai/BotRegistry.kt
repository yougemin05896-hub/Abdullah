package com.example.ai

data class BotProfile(val id: String, val name: String, val capabilities: List<String>)

class BotRegistry {
    private val standardBots = listOf(
        BotProfile("1", "Translation Bot", listOf("TEXT_TRANSLATION", "VOICE_TRANSLATION"))
    )
    fun getAvailableBots(): List<BotProfile> = standardBots
}
