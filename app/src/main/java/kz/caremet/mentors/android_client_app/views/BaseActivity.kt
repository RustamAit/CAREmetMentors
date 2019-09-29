package kz.caremet.mentors.android_client_app.views

import androidx.appcompat.app.AppCompatActivity
import kz.caremet.mentors.android_client_app.repository.webSocket.WebSocketConnector
import kz.caremet.mentors.android_client_app.repository.workers.WorkerManager

abstract class BaseActivity: AppCompatActivity() {

    override fun onStart() {
        WebSocketConnector.openWebSocket()
        WorkerManager.dispathPostEvents()
        super.onStart()
    }
}