package kz.caremet.mentors.android_client_app.di

import kz.caremet.mentors.android_client_app.core.data.AppDatabase
import kz.caremet.mentors.android_client_app.core.Constants
import kz.caremet.mentors.android_client_app.core.createService
import kz.caremet.mentors.android_client_app.core.createWebSocketService
import kz.caremet.mentors.android_client_app.repository.implementations.SessionRepositoryImpl
import kz.caremet.mentors.android_client_app.repository.interfaces.SessionRepository
import kz.caremet.mentors.android_client_app.repository.services.LoginService
import kz.caremet.mentors.android_client_app.repository.webSocket.WebSocketUpdateSevice
import kz.caremet.mentors.android_client_app.views.signIn.viewModel.SignInViewModel
import kz.caremet.mentors.android_client_app.views.signIn.viewModel.SignInViewModelImpl
import kz.caremet.mentors.android_client_app.views.signUp.viewModel.FragmentSharedViewModel
import kz.caremet.mentors.android_client_app.views.signUp.viewModel.FragmentSharedViewModelImpl
import kz.caremet.mentors.android_client_app.views.signUp.viewModel.InfoFragmentViewModelImpl
import kz.caremet.mentors.android_client_app.views.signUp.viewModel.InfoFragmentsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loginModule = module {
    single { createService<LoginService>(get(), Constants.baseUrl) }
    single { createWebSocketService<WebSocketUpdateSevice>(get(),
        "ws://167.71.33.195/cable",
        get(named("foregroundWithLogin"))
    ) }
    single { get<AppDatabase>().getMentorDao() }
    single { get<AppDatabase>().getEventDao() }
    single { get<AppDatabase>().getChatRoomDao() }
    single { get<AppDatabase>().getMessagesDao() }

    single { SessionRepositoryImpl(get(),get(),get()) as SessionRepository }
    single { FragmentSharedViewModelImpl() as FragmentSharedViewModel }
    single { InfoFragmentViewModelImpl(get()) as InfoFragmentsViewModel }
    single { SignInViewModelImpl(get()) as SignInViewModel }
}