package kz.caremet.mentors.android_client_app.repository.workers

import androidx.work.*
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.repository.dao.MessageDao
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref
import org.koin.core.KoinComponent
import org.koin.core.inject

object WorkerManager: KoinComponent {
    private val workManager = WorkManager.getInstance()
    val messageDao: MessageDao by inject()
    val sharedPref: LocalSharedPref by inject()

    fun dispathPostEvents(){
        messageDao.getChatRoomMessagesBySendStatus(DataEntities.StatusConstants.CREATED).subscribe {
            it.forEach {message ->
                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()

                val data = workDataOf(
                    "text" to message.text,
                    "uuid" to message.uuid,
                    "senderId" to sharedPref.getCurrentRealMentorId()
                )
                val oneTimeRequest = OneTimeWorkRequestBuilder<MessageWorker>()
                    .setInputData(data)
                    .setConstraints(constraints)
                    .build()
                workManager.enqueueUniqueWork("WITHOUT DOCUMENT SENDONG ${message.uuid}", ExistingWorkPolicy.KEEP,oneTimeRequest)
            }
        }
    }
}