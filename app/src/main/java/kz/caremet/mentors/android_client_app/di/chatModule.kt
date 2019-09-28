package kz.caremet.mentors.android_client_app.di

import kz.caremet.mentors.android_client_app.core.Constants
import kz.caremet.mentors.android_client_app.core.createService
import kz.caremet.mentors.android_client_app.core.data.AppDatabase
import kz.caremet.mentors.android_client_app.repository.implementations.ChatRepositoryImpl
import kz.caremet.mentors.android_client_app.repository.interfaces.ChatRepositoty
import kz.caremet.mentors.android_client_app.repository.services.ChatRoomService
import org.koin.dsl.module

val chatModule = module {
    single { get<AppDatabase>().getChatRoomDao() }
    single { get<AppDatabase>().getMessagesDao() }
    single { createService<ChatRoomService>(get(), Constants.baseUrl) }
    single { ChatRepositoryImpl(get(),get(),get(),get()) as ChatRepositoty }
}