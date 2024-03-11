package com.asiasama.chatapp.di

import org.koin.dsl.module


fun appModule() = module {
    includes(
            RepositoryModule,
            viewModelModule,
    )

}
