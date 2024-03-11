package com.asiasama.chatapp.ui.screen.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asiasama.chatapp.domain.ChatRepository
import com.asiasama.chatapp.domain.entity.NewMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class ChatViewModel(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ChatUiState())
    val state = _state.asStateFlow()

    init {
        getMessages()
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val userInfo = chatRepository.getSenderInfo()
            _state.update {
                it.copy(
                        user = it.user.copy(
                                id = userInfo.id,
                                name = userInfo.username,
                                profileUrl = userInfo.imageUrl,
                                isOnline = userInfo.isOnline
                        )
                )
            }
        }
    }

    private fun getMessages() {
        viewModelScope.launch {
            chatRepository.getMessages("123").collect { messages ->
                _state.update { it.copy(messages = messages.toUiState()) }
            }
            Log.e("TAG", "getMessages: ${state.value.messages}", )
        }
    }

    fun onSendClicked() {
        viewModelScope.launch {
            chatRepository.sendMessage(
                    NewMessage(
                            senderId = "1",
                            message = state.value.message,
                            receiverId = "123"
                    )
            )
        }

        _state.update { it.copy(message = "") }
    }

    fun onChanceMessage(newValue: String) {
        _state.update { it.copy(message = newValue) }
    }

}
