package com.asiasama.chatapp.ui.screen.chat.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.asiasama.chatapp.R
import com.asiasama.chatapp.ui.theme.Theme

@Composable
fun ChatAppBar(
    modifier: Modifier = Modifier,
    onCLickBack: () -> Unit,
    profileUrl: String,
    name: String,
    isOnline: Boolean
) {
    Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Theme.colors.primary)
                .then(modifier)
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        IconButton(onClick = onCLickBack) {
            Icon(
                    painter = painterResource(id = R.drawable.icon_back),
                    contentDescription = null,
                    tint = Theme.colors.onPrimary
            )
        }
        UserInfo(profileUrl = profileUrl, name = name, isOnline = isOnline)
        Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.End),
        ) {
            IconButton(onClick = { /*TODO navigate to screen call*/ }) {
                Icon(
                        painter = painterResource(id = R.drawable.icon_call),
                        contentDescription = null,
                        tint = Theme.colors.onPrimary

                )
            }
            IconButton(onClick = { /*TODO navigate to video call*/ }) {
                Icon(
                        painter = painterResource(id = R.drawable.icon_video_call),
                        contentDescription = null,
                        tint = Theme.colors.onPrimary
                )
            }
        }
    }
}

@Composable
private fun UserInfo(
    profileUrl: String,
    name: String,
    isOnline: Boolean
) {
    val userStatus =
        if (isOnline) stringResource(R.string.online) else stringResource(R.string.last_seen_recently)
    Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
                modifier = Modifier.size(42.dp),
                contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = CircleShape),
                    painter = rememberAsyncImagePainter(model = profileUrl),
                    contentDescription = name,
                    contentScale = ContentScale.Crop
            )

            this@Row.AnimatedVisibility(visible = isOnline) {
                Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(shape = CircleShape)
                            .background(Theme.colors.surface)
                            .padding(1.dp)
                            .clip(shape = CircleShape)
                            .background(Theme.colors.green),
                )
            }
        }

        Column {
            Text(
                    text = name,
                    style = Theme.typography.bodyLarge,
                    color = Theme.colors.onPrimary,
            )
            Text(
                    text = userStatus,
                    style = Theme.typography.labelLarge,
                    color = Theme.colors.onPrimary,
            )
        }
    }
}

@Preview
@Composable
fun PreviewAppBar() {
    ChatAppBar(
            profileUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGuH6Vo5XDGGvgriYJwqI9I8efWEOeVQrVTw&usqp=CAU",
            name = "Sama",
            isOnline = true,
            onCLickBack = {}
    )
}