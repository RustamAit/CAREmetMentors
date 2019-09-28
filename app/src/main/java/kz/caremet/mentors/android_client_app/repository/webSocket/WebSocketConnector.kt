package kz.caremet.mentors.android_client_app.repository.webSocket

import android.util.Log
import com.tinder.scarlet.websocket.WebSocketEvent
import kz.caremet.mentors.android_client_app.repository.dao.ChatRoomDao
import kz.caremet.mentors.android_client_app.repository.dao.MessageDao
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref
import org.koin.core.KoinComponent
import org.koin.core.inject

object WebSocketConnector: KoinComponent {

    val webSocketService: WebSocketUpdateSevice by inject()
    val sharedPref: LocalSharedPref by inject()
    val chatRoomDao: ChatRoomDao by inject()
    val messageDao: MessageDao by inject()

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
            it.message?.web_socket_data?.chat_rooms?.let {
                Log.d("TEST_SOCKETA",it[0].name)

                it.forEach {
                    chatRoomDao.upsertDeal(it.serializeForDb(sharedPref.getCurrentMentorId()!!))
                    it.messages.forEach {
                        messageDao.upsertDeal(it.serializeForDb())
                    }
                }
                it
            }
            it.message?.web_socket_data?.events?.let {
                Log.d("TEST_SOCKETA",it[0].id.toString())
            }
//            it.message?.web_socket_data?.let {
//                Single.fromCallable {
//                    it.questions?.forEach {question ->
//                        mainRepositories.saveQuestions(question)
//                    }
//                }.onErrorReturn { Unit }.subscribe()
//
//                Single.fromCallable {
//                    it.answers?.forEach {answer ->
//                        mainRepositories.saveQuestionAnswer(answer , true)
//                    }
//                }.onErrorReturn { Unit }.subscribe()
//
//                Single.fromCallable {
//                    it.tasks?.forEach { task ->
//                        mainRepositories.saveTasks(task)
//                    }
//                }.onErrorReturn { Unit }.subscribe()
//            }
        }.subscribe()
    }

    fun sendUpdateSince(){
        webSocketService.sendSince(
            SendSince("message",action = "received",
                data = "{\"entity_id\": \"125\", \"since\":\"${null?:""}\"}",
                identifier =  "{\"channel\":\"MainChannel\", \"mentor_id\":\"${sharedPref.getCurrentRealMentorId()}\"}")

        )
    }
}