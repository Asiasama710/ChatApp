package com.asiasama.chatapp.data

import com.asiasama.chatapp.data.model.MessageDto
import com.asiasama.chatapp.data.model.toDto
import com.asiasama.chatapp.data.model.toEntity
import com.asiasama.chatapp.domain.ChatRepository
import com.asiasama.chatapp.domain.entity.ChatHistory
import com.asiasama.chatapp.domain.entity.Message
import com.asiasama.chatapp.domain.entity.NewMessage
import com.asiasama.chatapp.domain.entity.UserInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlin.random.Random

class ChatRepositoryImpl (): ChatRepository {

    private val messages = MutableStateFlow(
            listOf(
                    MessageDto(
                            id = "1",
                            message = "Hello, how are you ? Ramadan Karem!",
                            senderId = "123",
                            senderAvatar = "",
                    ),
                    MessageDto(
                            id = "1",
                            message = "I wish you a happy Ramadan!",
                            senderId = "123",
                            senderAvatar = ""
                    ),
                    MessageDto(
                            id = "1",
                            message = "I wish you a happy Ramadan!",
                            senderId = "1",
                            senderAvatar = ""
                    ),
            )
    )
    private val user =
        UserInfo(
                id = "123",
                username = "Sama Doe",
                imageUrl = "https://randomuser.me/api/portraits/women/2.jpg",
                isOnline = true
        )
    override  fun getSenderInfo(): UserInfo {
        return user
    }


    override fun getMessages(senderId: String): Flow<List<Message>> {
        return messages.map {
            it.filter { messagesDto ->
                messagesDto.senderId == senderId
            }.map { messageDto ->
                messageDto.toEntity()
            }
        }
    }

    override suspend fun sendMessage(message: NewMessage) {
        messages.value += message.toDto()
        delay(1000)
        answerWithFakeMessage(message)
    }

    private fun answerWithFakeMessage(message: NewMessage) {
        val fakeAnswers = listOf(
                "Ok, thank you for your response.",
                "I will check it.",
                "I will get back to you soon.",
                "That's great.",
                "Seems like a good idea.",
        )
        messages.value += MessageDto(
                id = Random.nextInt().toString(),
                message = fakeAnswers.random(),
                senderId =message.senderId,
                senderAvatar = ""
        )
    }
}