package kz.caremet.mentors.android_client_app.repository.implementations

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.repository.dao.ChatRoomDao
import kz.caremet.mentors.android_client_app.repository.dao.MessageDao
import kz.caremet.mentors.android_client_app.repository.interfaces.ChatRepositoty
import kz.caremet.mentors.android_client_app.repository.services.ChatRoomService
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref

class ChatRepositoryImpl(val chatRoomDao: ChatRoomDao,
                         val messageDao: MessageDao,
                         val sharedPref: LocalSharedPref,
                         val chatRoomService: ChatRoomService): ChatRepositoty{
    override fun getChatRooms(): Single<List<DataEntities.ChatRoomFromApi>> {
        return chatRoomService.getChatRooms(sharedPref.getAccessToken(),sharedPref.getCurrentRealMentorId()).subscribeOn(Schedulers.io())
            .map {
                it.forEach {
                    chatRoomDao.upsertDeal(it.serializeForDb(sharedPref.getCurrentMentorId()!!))
                    it.messages.forEach {
                        messageDao.upsertDeal(it.serializeForDb())
                    }
                }
                it
            }

    }

    override fun getChatRoomsFromDB(): Observable<List<DataEntities.ChatRoomFromDb>> {
        return chatRoomDao.getUserChatRooms(sharedPref.getCurrentMentorId()!!)
    }

    override fun getChatRoomMessagesInDb(chatRoomId: Int): Observable<List<DataEntities.MessageFormDb>> {
        return messageDao.getChatRoomMessages(chatRoomId)
    }

}