package com.asiasama.chatapp.domain

import com.asiasama.chatapp.domain.entity.Message
import com.asiasama.chatapp.domain.entity.NewMessage
import com.asiasama.chatapp.domain.entity.UserInfo
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun getMessages(senderId: String): Flow<List<Message>>
    suspend fun sendMessage(message: NewMessage)
    fun getSenderInfo(): UserInfo
}