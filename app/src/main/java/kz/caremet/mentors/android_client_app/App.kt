package kz.caremet.mentors.android_client_app

import android.app.Application
import kz.caremet.mentors.android_client_app.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
            androidContext(this@App)
        }
    }
}