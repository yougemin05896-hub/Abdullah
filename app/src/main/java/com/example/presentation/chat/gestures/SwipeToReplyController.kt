package com.example.presentation.chat.gestures

class SwipeToReplyController {
    fun calculateSpringResistance(dragAmount: Float): Float {
        // Natural physics for swipe-to-reply gesture like Telegram
        return dragAmount * 0.4f
    }
}
