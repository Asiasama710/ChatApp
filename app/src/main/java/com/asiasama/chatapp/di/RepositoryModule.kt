package com.asiasama.chatapp.di

import com.asiasama.chatapp.data.ChatRepositoryImpl
import com.asiasama.chatapp.domain.ChatRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val RepositoryModule = module {
    singleOf(::ChatRepositoryImpl) { bind<ChatRepository>() }
}
