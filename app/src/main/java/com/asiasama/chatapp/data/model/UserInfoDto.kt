package com.asiasama.chatapp.data.model

import com.asiasama.chatapp.domain.entity.UserInfo

data class UserInfoDto(
    val id: String,
    val username: String,
    val image: String,
    val isOnline: Boolean
)

fun UserInfoDto.toEntity(): UserInfo {
    return UserInfo(
            id = id,
            username = username,
            imageUrl = image,
            isOnline = isOnline
    )
}