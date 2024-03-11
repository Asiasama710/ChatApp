package com.asiasama.chatapp.ui.screen.chat

import com.asiasama.chatapp.domain.entity.Message


data class ChatUiState(
    val user: UserUiState = UserUiState(),
    val messages: List<MessageUIState> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false,
    val error: String? = "",
) {

}

data class UserUiState(
    val id: String = "",
    val name: String = "",
    val profileUrl: String = "",
    val isOnline: Boolean = false,
)

data class MessageUIState(
    val id: String = "",
    val isMe: Boolean = false,
    val message: String = "",
)

fun Message.toUiState(): MessageUIState {
    return MessageUIState(
            id = id,
            isMe = isMe,
            message = message
    )
}

fun List<Message>.toUiState(): List<MessageUIState> = map(Message::toUiState)