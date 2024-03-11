package com.asiasama.chatapp.ui.theme

import androidx.compose.ui.graphics.Color

data class Colors(
    val primary: Color,
    val contentPrimary: Color,
    val contentTertiary: Color,
    val surface: Color,
    val onPrimary:Color,
    val background: Color,
    val pink: Color,
    val onSurface: Color,
)

val LightColors = Colors(
        primary = Color(0xFF4B69D0),
        contentPrimary = Color(0xDE000A1F),
        contentTertiary = Color(0x61000B1F),
        surface = Color(0xFFFFFFFF),
        background = Color(0xFFFAFAFA),
        pink = Color(0xFFFFD0CC),
        onPrimary = Color(0xFFECF2FF),
        onSurface = Color(0xDEFFFFFF)
)

val DarkColors = Colors(
        primary = Color(0xFF4B69D0),
        contentPrimary = Color(0xDEFFFFFF),
        contentTertiary = Color(0x61FFFFFF),
        surface = Color(0xFF1C1C1C),
        background = Color(0xFF151515),
        pink = Color(0xFF261F1F),
        onPrimary = Color(0xFFECF2FF),
        onSurface = Color(0xDEFFFFFF)

)