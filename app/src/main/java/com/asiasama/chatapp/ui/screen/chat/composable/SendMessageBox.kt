package com.asiasama.chatapp.ui.screen.chat.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.asiasama.chatapp.R
import com.asiasama.chatapp.ui.theme.Theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMessageBox(
    text: String,
    isRecording: Boolean,
    isRecordingPause: Boolean,
    onClickRecordVoice: () -> Unit,
    onClickSendButton: () -> Unit,
    onTextChanged: (String) -> Unit,
    onClickPauseRecord: () -> Unit,
    onClickCancelRecord: () -> Unit,
    onClickContinueRecord: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val icon = animateIntAsState(
                targetValue = if (isRecordingPause) R.drawable.icon_continue_record else R.drawable.icon_pause,
                label = ""
        )

    Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Theme.colors.background),
            verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        AnimatedVisibility(visible = isRecording) {
            Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedContent(targetState = isRecordingPause,modifier =Modifier.align(Alignment.CenterVertically)) { isRecordingPause ->
                    if (isRecordingPause) {
                        VoiceItem(
                                modifier = Modifier,
                                onClickPlayRecord = {}
                        )

                    } else {
                        RecordItem(
                                isRecording = isRecording,
                                modifier = Modifier
                        )
                    }
                }

            }
        }

        Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = Theme.colors.background)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AnimatedContent(targetState = isRecording, modifier = if(!isRecording) Modifier.weight(1f) else Modifier, label = ""
            ) { isRecording ->
                if (isRecording) {
                    Icon(
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .clickable { onClickCancelRecord() }
                                .padding(8.dp),
                            painter = painterResource(R.drawable.icon_cancel),
                            contentDescription = stringResource(R.string.icon_cancel_record),
                            tint = Theme.colors.primary
                    )
                } else {
                    TextField(
                            modifier = Modifier,
                            value = text,
                            onValueChange = onTextChanged,
                            shape = RoundedCornerShape(24.dp),
                            placeholder = { Text(text = stringResource(R.string.start_typing),color =Theme.colors.contentTertiary) },
                            colors = TextFieldDefaults.textFieldColors(
                                    disabledTextColor = Color.Transparent,
                                    containerColor = Theme.colors.surface,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    cursorColor = Theme.colors.contentTertiary,
                            ),
                            textStyle = Theme.typography.bodyMedium.copy(color = Theme.colors.contentPrimary),
                            singleLine = true,
                    )
                }
            }

            AnimatedVisibility(text.isEmpty() && !isRecording) {
                Icon(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .clickable { onClickRecordVoice() }
                            .padding(8.dp),
                        painter = painterResource(id = R.drawable.icon_mic),
                        contentDescription = stringResource(R.string.mice_icon),
                        tint = Theme.colors.primary
                )
            }
            AnimatedVisibility( isRecording) {
                Icon(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .clickable { if (isRecordingPause) onClickContinueRecord() else onClickPauseRecord() }
                            .padding(8.dp),
                        painter = painterResource(id = icon.value),
                        contentDescription = stringResource(R.string.mice_icon),
                        tint = Theme.colors.primary
                )
            }
            AnimatedVisibility(text.isNotEmpty() || isRecording) {
                Icon(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .clickable { onClickSendButton() }
                            .padding(8.dp),
                        painter = painterResource(id = R.drawable.paper_airplane),
                        contentDescription = null,
                        tint = Theme.colors.primary,
                )
            }
        }
    }

}



@Composable
private fun RecordItem(isRecording: Boolean,modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.waveform_line))
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
            initialValue = Color.Gray,
            targetValue = Color.Red,
            animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 900, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
            ), label = ""
    )
    Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        Spacer(
                modifier = Modifier
                    .size(14.dp)
                    .clip(shape = CircleShape)
                    .background(Theme.colors.surface)
                    .padding(1.dp)
                    .clip(shape = CircleShape)
                    .background(color),

                )
        Text(
                text = "Recording",
                modifier = Modifier.padding(8.dp),
                color = Theme.colors.contentPrimary
        )
        LottieAnimation(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                composition = composition,
                iterations = LottieConstants.IterateForever,
                isPlaying = isRecording,
                speed = 0.8f,
        )
    }
}

@Preview(showBackground = true, locale = "en")
@Composable
fun DefaultTextFieldPreview() {
    SendMessageBox(
            text = "",
            isRecording = false,
            isRecordingPause = false,
            onClickRecordVoice = {},
            onClickSendButton = {},
            onTextChanged = {},
            onClickPauseRecord = {},
            onClickCancelRecord = {},
            onClickContinueRecord = {}
    )
}

