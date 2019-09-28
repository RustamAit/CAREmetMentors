package kz.caremet.mentors.android_client_app.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import kz.caremet.mentors.android_client_app.core.AppDatabase
import kz.caremet.mentors.android_client_app.core.Constants
import kz.caremet.mentors.android_client_app.core.createOkHttpClient
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPrefImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coreModule = module {
    single( definition = {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "caremetMentors-database").fallbackToDestructiveMigration()
            .build()
    })
    single {
        createSharedPreferences(androidContext())
    }
    single { LocalSharedPrefImpl(get()) as LocalSharedPref }

    single { createOkHttpClient() }


   // single(named("foregroundWithLogin")) { AndroidLifecycle.ofApplicationForeground(get()).combineWith(LoggedInLifecycle())}
}


val appModules = listOf(coreModule, loginModule)

fun createSharedPreferences(context: Context) : SharedPreferences {
    return context.applicationContext.getSharedPreferences(Constants.preference, Context.MODE_PRIVATE)
}
