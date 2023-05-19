package com.ead.project.ourivesariarumor.domain.model.chat

data class Message(
    val id : Long,
    val senderId : String,
    val receiverId : String,
    val text : String,
    val timeStamp : Long,
)
