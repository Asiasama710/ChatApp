package com.asiasama.chatapp.domain.entity

data class NewMessage(
    val senderId: String,
    val receiverId: String,
    val message: String,
)
