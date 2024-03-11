package com.asiasama.chatapp.ui.screen.chat.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asiasama.chatapp.R
import com.asiasama.chatapp.ui.theme.Theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTextField(
    text: String,
    onClickRecordVoice: () -> Unit,
    onClickSendButton: () -> Unit,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
            modifier = modifier
                .fillMaxWidth().background(color = Theme.colors.background)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                    16.dp,
                    alignment = Alignment.CenterHorizontally
            )
    ) {
        TextField(
                modifier = modifier.weight(1f),
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
        AnimatedVisibility(text.isEmpty()) {
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
        AnimatedVisibility(text.isNotEmpty()) {
            Icon(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .clickable(enabled = text.isNotEmpty()) { onClickSendButton() }
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.paper_airplane),
                    contentDescription = null,
                    tint = if (text.isNotEmpty()) Theme.colors.primary else Color.Gray,
            )
        }


    }
}

@Preview(showBackground = true, locale = "en")
@Composable
fun DefaultTextFieldPreview() {
    ChatTextField(
            text = "",
            onClickRecordVoice = {},
            onTextChanged = {},
            onClickSendButton = {},
    )
}