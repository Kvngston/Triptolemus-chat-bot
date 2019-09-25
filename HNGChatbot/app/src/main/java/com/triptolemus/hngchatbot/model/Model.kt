package com.triptolemus.hngchatbot.model

object Model {
    data class ChatMessage(val msgUser: String,
                           val msgText: String)
}