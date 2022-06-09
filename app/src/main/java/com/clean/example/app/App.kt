package com.clean.example.app

import android.app.Application
import com.clean.example.BuildConfig
import com.clean.example.di.dataModule
import com.clean.example.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level
import timber.log.Timber

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(dataModule, presentationModule)
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}