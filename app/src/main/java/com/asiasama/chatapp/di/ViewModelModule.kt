package com.asiasama.chatapp.di

import com.asiasama.chatapp.ui.screen.chat.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val viewModelModule = module {
    viewModelOf(::ChatViewModel)
}
