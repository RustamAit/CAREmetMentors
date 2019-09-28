package kz.caremet.mentors.android_client_app.repository.webSocket

import com.tinder.scarlet.websocket.WebSocketEvent
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable
import io.reactivex.Observable
import kz.caremet.mentors.android_client_app.core.data.WebSocketMessage

interface WebSocketUpdateSevice {
    @Send
    fun sendSubscribe(subscribe: Subscribe)

    @Receive
    fun getUpdates(): Observable<WebSocketMessage>

    @Receive
    fun observeOnConnectionOpenedEvent(): Flowable<WebSocketEvent>

    @Send
    fun sendSince(since: SendSince)
}

data class Subscribe(
    val command: String,
    val identifier: String
)

data class SendSince(
    val command: String = "message",
    val data: String,
    val action: String,
    val identifier: String
)