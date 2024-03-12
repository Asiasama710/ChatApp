package com.asiasama.chatapp.ui.screen.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asiasama.chatapp.domain.ChatRepository
import com.asiasama.chatapp.domain.entity.NewMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
        }
    }

    fun onRecordVoiceClicked() {
        _state.update { it.copy(isRecording = true) }
    }

    fun onSendClicked() {
        _state.update { it.copy(isRecording = false) }
        viewModelScope.launch {
            chatRepository.sendMessage(
                    NewMessage(
                            senderId = "1",
                            message = state.value.message,
                            receiverId = "123"
                    )
            )
        }

        _state.update { it.copy(message = "", isRecordingPause = false) }
    }

    fun onChanceMessage(newValue: String) {
        _state.update { it.copy(message = newValue) }
    }

    fun onCancelRecordClicked() {
        _state.update { it.copy(isRecording = false, isRecordingPause = false) }
    }

    fun onPauseRecordClicked() {
        _state.update { it.copy(isRecordingPause = true) }
    }

    fun onContinueRecordClicked() {
        _state.update { it.copy(isRecordingPause = false) }
    }

}
