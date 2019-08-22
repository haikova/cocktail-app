package olyarisu.github.com.cocktailapp

import android.app.Application
import olyarisu.github.com.cocktailapp.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(applicationModule)
        }
    }
}