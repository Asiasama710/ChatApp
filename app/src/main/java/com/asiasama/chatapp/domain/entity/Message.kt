package com.asiasama.chatapp.domain.entity

data class Message(
    val id: String,
    val message: String,
    val isMe: Boolean,
    val avatarUrl: String,
)
