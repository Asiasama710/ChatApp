package com.asiasama.chatapp.ui.screen.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.asiasama.chatapp.R
import com.asiasama.chatapp.ui.screen.chat.composable.ChatAppBar
import com.asiasama.chatapp.ui.screen.chat.composable.SendMessageBox
import com.asiasama.chatapp.ui.screen.chat.composable.MessageCard
import com.asiasama.chatapp.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel
import kotlin.math.abs


@SuppressLint("UnrememberedMutableState")
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = koinViewModel(),
) {

    val state by viewModel.state.collectAsState()

    ChatContent(
            state = state,
            messageText = state.message,
            onValueChanged = viewModel::onChanceMessage,
            onSendMessage = viewModel::onSendClicked,
            onCLickBack = { /*TODO back to screen chats*/ },
            onClickRecordVoice = viewModel::onRecordVoiceClicked,
            onClickCancelRecord = viewModel::onCancelRecordClicked,
            onClickPauseRecord = viewModel::onPauseRecordClicked,
            onClickContinueRecord = viewModel::onContinueRecordClicked,
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ChatContent(
    state: ChatUiState,
    messageText: String,
    onValueChanged: (String) -> Unit,
    onSendMessage: () -> Unit,
    onCLickBack: () -> Unit,
    onClickRecordVoice: () -> Unit,
    onClickCancelRecord: () -> Unit,
    onClickPauseRecord: () -> Unit,
    onClickContinueRecord: () -> Unit,
) {
    val listState = rememberLazyListState()
    val isDarkTheme = isSystemInDarkTheme()
    Scaffold(
            topBar = {
                ChatAppBar(
                        modifier = Modifier.padding(top = 24.dp),
                        onCLickBack = onCLickBack,
                        profileUrl = state.user.profileUrl,
                        name = state.user.name,
                        isOnline = state.user.isOnline
                )
            },
    ) { padding ->
        LaunchedEffect(state.messages.size) {
            listState.animateScrollToItem(abs(state.messages.size - 1))
        }
        Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                ) {
            Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = if (isDarkTheme) painterResource(id = R.drawable.background_chat_dark) else painterResource(id = R.drawable.background_chat_light),
                    contentDescription = "background chat screen",
                    contentScale = ContentScale.Crop,
            )
            Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn(
                        state = listState,
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Bottom,
                        contentPadding = PaddingValues(vertical = 24.dp),
                ) {
                    items(items = state.messages) {
                        MessageCard(it, Modifier.animateItemPlacement())
                    }

                }
                SendMessageBox(
                        text = messageText,
                        isRecording = state.isRecording,
                        isRecordingPause = state.isRecordingPause,
                        onClickRecordVoice = onClickRecordVoice,
                        onClickSendButton = onSendMessage,
                        onTextChanged = onValueChanged,
                        onClickPauseRecord = onClickPauseRecord,
                        onClickCancelRecord = onClickCancelRecord,
                        onClickContinueRecord = onClickContinueRecord
                )
            }
        }

    }

}