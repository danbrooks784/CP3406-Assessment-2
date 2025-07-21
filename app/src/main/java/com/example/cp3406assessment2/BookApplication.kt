package com.example.cp3406assessment2

import android.app.Application
import com.example.cp3406assessment2.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class BookApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(appModules)
        }
    }
}