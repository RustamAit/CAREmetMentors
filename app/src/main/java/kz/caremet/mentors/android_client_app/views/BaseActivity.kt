package kz.caremet.mentors.android_client_app.views

import androidx.appcompat.app.AppCompatActivity
import kz.caremet.mentors.android_client_app.repository.webSocket.WebSocketConnector

abstract class BaseActivity: AppCompatActivity() {

    override fun onStart() {
        WebSocketConnector.openWebSocket()
        super.onStart()
    }
}