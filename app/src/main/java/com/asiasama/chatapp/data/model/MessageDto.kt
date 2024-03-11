package com.asiasama.chatapp.data.model

import com.asiasama.chatapp.domain.entity.Message
import com.asiasama.chatapp.domain.entity.NewMessage
import kotlin.random.Random

data class MessageDto(
    val id: String? = null,
    val message: String? = null,
    val senderId: String? = null,
    val senderAvatar: String? = null,
)

fun MessageDto.toEntity(): Message {
    return Message(
            id = id ?: "",
            message = message ?: "",
            avatarUrl = senderAvatar ?: "",
            isMe = senderId == "1",
    )
}

fun List<MessageDto>.toEntity(): List<Message> {
    return map { it.toEntity() }
}

fun NewMessage.toDto(): MessageDto {
    return MessageDto(
            id = Random.nextInt(0, 1000000).toString(),
            message = message,
            senderId = senderId,
    )
}