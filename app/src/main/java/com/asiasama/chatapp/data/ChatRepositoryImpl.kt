package com.asiasama.chatapp.data

import android.util.Log
import com.asiasama.chatapp.data.model.MessageDto
import com.asiasama.chatapp.data.model.toDto
import com.asiasama.chatapp.data.model.toEntity
import com.asiasama.chatapp.domain.ChatRepository
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
                            message = "Hello, how are you? can I ask you something?",
                            senderId = "1",
                            senderAvatar = "",
                    ),
                    MessageDto(
                            id = "1",
                            message = "Yes, sure. What do you want to ask?",
                            senderId = "123",
                            senderAvatar = ""
                    ),
                    MessageDto(
                            id = "1",
                            message = "",
                            senderId = "1",
                            senderAvatar = ""
                    ),
                    MessageDto(
                            id = "1",
                            message = "",
                            senderId = "123",
                            senderAvatar = ""
                    ),
            )
    )
    private val user =
        UserInfo(
                id = "123",
                username = "Asia Sama",
                imageUrl = "https://randomuser.me/api/portraits/women/2.jpg",
                isOnline = true
        )
    override  fun getSenderInfo(): UserInfo {
        return user
    }


    override fun getMessages(senderId: String): Flow<List<Message>> {
        return messages.map {
            it.map { messageDto ->
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
                "Yes, That's great.",
                "",
                "I'm not sure.",
        )
        messages.value += MessageDto(
                id = Random.nextInt().toString(),
                message = fakeAnswers.random(),
                senderId =message.receiverId,
                senderAvatar = ""
        )
    }
}