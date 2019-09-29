package kz.caremet.mentors.android_client_app.repository.webSocket

import android.util.Log
import com.tinder.scarlet.websocket.WebSocketEvent
import kz.caremet.mentors.android_client_app.repository.dao.ChatRoomDao
import kz.caremet.mentors.android_client_app.repository.dao.EventDao
import kz.caremet.mentors.android_client_app.repository.dao.MessageDao
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref
import org.koin.core.KoinComponent
import org.koin.core.inject

object WebSocketConnector: KoinComponent {

    val webSocketService: WebSocketUpdateSevice by inject()
    val sharedPref: LocalSharedPref by inject()
    val chatRoomDao: ChatRoomDao by inject()
    val messageDao: MessageDao by inject()
    val eventDao: EventDao by inject()

    fun openWebSocket(){
        webSocketService.observeOnConnectionOpenedEvent().filter {
            it is WebSocketEvent.OnConnectionOpened
        }.subscribe {
            webSocketService.sendSubscribe(
                Subscribe(
                    "subscribe",
                    "{\"channel\":\"MainChannel\", \"mentor_id\":\"${sharedPref.getCurrentRealMentorId()}\"}"
                )
            )
            sendUpdateSince()
        }

        webSocketService.getUpdates().doOnNext {
            Log.d("TEST_SOCKETA","asdasdasda")
            Log.d("TEST_SOCKETA", it.toString())

            it.message?.web_socket_data?.let{data ->

                data.messages?.forEach {
                    messageDao.upsertDeal(it.serializeForDb())
                }

                data.events?.forEach {
                    eventDao.upsertDeal(it.serializeForDb())
                }

            }


        }.subscribe()
    }

    fun sendUpdateSince(){
        webSocketService.sendSince(
            SendSince("message",action = "received",
                data = "{\"since\":\"${null?:""}\"}",
                identifier =  "{\"channel\":\"MainChannel\", \"mentor_id\":\"${sharedPref.getCurrentRealMentorId()}\"}")

        )
    }
}