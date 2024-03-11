package com.asiasama.chatapp.ui

import android.app.Application
import com.asiasama.chatapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class ChatApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ChatApp)
            modules(appModule())
        }
    }

}
