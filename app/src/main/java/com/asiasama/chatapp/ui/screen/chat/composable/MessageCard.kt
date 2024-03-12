package com.asiasama.chatapp.ui.screen.chat.composable

import android.os.Build
import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.asiasama.chatapp.ui.screen.chat.MessageUIState
import com.asiasama.chatapp.ui.theme.Theme

@Composable
fun MessageCard(
    message: MessageUIState,
    modifier: Modifier = Modifier,
) {

    Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .then(modifier),
            contentAlignment = if (message.isMe) Alignment.CenterEnd else Alignment.CenterStart,
    ) {

        if (message.message.isNotEmpty()){
            Text(
                    modifier = Modifier
                        .background(
                                color = if (message.isMe) {
                                    Theme.colors.primary
                                } else {
                                    Theme.colors.surface
                                },
                                shape = RoundedCornerShape(
                                        topStart = 12.dp,
                                        topEnd = 12.dp,
                                        bottomStart = if (message.isMe) 12.dp else 0.dp,
                                        bottomEnd = if (!message.isMe) 12.dp else 0.dp
                                )
                        )
                        .padding(16.dp)
                    ,
                    text = message.message.parseHtml(),
                    style = Theme.typography.bodyMedium,
                    textAlign = if (message.isMe) {
                        TextAlign.End
                    } else {
                        TextAlign.Start
                    },
                    color = if (message.isMe) {
                        Theme.colors.onPrimary
                    } else {
                        Theme.colors.contentPrimary
                    },
            )
        }else{
            Box(Modifier
                        .background(
                                color = if (message.isMe) {
                                    Theme.colors.primary
                                } else {
                                    Theme.colors.surface
                                },
                                shape = RoundedCornerShape(
                                        topStart = 12.dp,
                                        topEnd = 12.dp,
                                        bottomStart = if (message.isMe) 12.dp else 0.dp,
                                        bottomEnd = if (!message.isMe) 12.dp else 0.dp
                                )
                        )
                    ,
            ) {
                VoiceItem (
                        onClickPlayRecord = { /*TODO play record*/ },
                        modifier = Modifier.width(250.dp),
                        playIconColor = if (message.isMe) {
                            Theme.colors.onPrimary
                        } else {
                            Theme.colors.primary
                        },
                        voiceLineColor = if (message.isMe) {
                            Theme.colors.onPrimary
                        } else {
                            Theme.colors.primary
                        },
                        backgroundColor = if (message.isMe) {
                            Theme.colors.primary
                        } else {
                            Theme.colors.surface
                        }
                )
            }
        }

    }
}
fun String.parseHtml(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(this).toString()
    }
}
