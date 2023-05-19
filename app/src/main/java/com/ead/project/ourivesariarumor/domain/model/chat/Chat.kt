package com.ead.project.ourivesariarumor.domain.model.chat

data class Chat(
    val id : Long,
    val sellerId : String,
    val userId : String,
    val messages : List<Message>
)
