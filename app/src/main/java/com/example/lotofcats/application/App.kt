package com.example.lotofcats.application

import android.app.Application
import com.example.lotofcats.di.appmodule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { modules(appmodule) }
    }
}