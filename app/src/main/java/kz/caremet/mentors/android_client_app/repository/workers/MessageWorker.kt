package kz.caremet.mentors.android_client_app.repository.workers

import android.content.Context
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import io.reactivex.Single
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.repository.dao.MessageDao
import kz.caremet.mentors.android_client_app.repository.services.ChatRoomService
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref
import org.koin.core.KoinComponent
import org.koin.core.inject

class MessageWorker(context: Context,
                    workerParameters: WorkerParameters)
    : RxWorker(context,workerParameters),
    KoinComponent
{

    val chatRoomService: ChatRoomService by inject()
    val messageDao: MessageDao by inject()
    val sharedPref: LocalSharedPref by inject()

    override fun createWork(): Single<Result> {
        val text = inputData.keyValueMap["text"] as String
        val uuid = inputData.keyValueMap["uuid"] as String
        val senderId = inputData.keyValueMap["senderId"] as Int
        val chatRoomId = inputData.keyValueMap["chatRoomId"] as Int
        return chatRoomService.postMessage(sharedPref.getAccessToken() ,sharedPref.getCurrentRealMentorId(),chatRoomId,DataEntities.MessageForPost(
            uuid,text,senderId
        )).map {
            messageDao.upsertDeal(it.serializeForDb())
            Result.success()
        }.onErrorReturn {
            Result.failure()
        }
    }

}