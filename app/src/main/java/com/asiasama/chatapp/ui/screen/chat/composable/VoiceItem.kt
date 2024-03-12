package com.asiasama.chatapp.ui.screen.chat.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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

@Composable
fun VoiceItem(
    modifier: Modifier = Modifier,
    onClickPlayRecord: () -> Unit,
    backgroundColor: Color = Theme.colors.onSurface,
    playIconColor: Color = Theme.colors.primary,
    voiceLineColor: Color = Theme.colors.primary
){
    Row(
            modifier = modifier
                .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(24.dp)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .padding(vertical = 8.dp)
                    .clip(shape = CircleShape)
                    .clickable { onClickPlayRecord() }
                    .padding(8.dp),
                painter = painterResource(R.drawable.play),
                contentDescription = stringResource(R.string.icon_cancel_record),
                tint = playIconColor
        )

        Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(voiceLineColor)
            )
            HorizontalDivider(
                    thickness = 4.dp,
                    modifier = Modifier.fillMaxWidth(),
                    color = voiceLineColor
            )
        }


    }
}
@Preview
@Composable
fun CircleAndLine() {
    VoiceItem(onClickPlayRecord = { /*TODO*/ })
}

