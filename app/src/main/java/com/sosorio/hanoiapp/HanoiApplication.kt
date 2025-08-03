package com.sosorio.hanoiapp

import android.app.Application
import com.sosorio.hanoiapp.di.appModule
import com.sosorio.hanoiapp.di.dispatcherModule
import com.sosorio.hanoiapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HanoiApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@HanoiApplication)
            modules(networkModule, appModule, dispatcherModule)
        }
    }
}
