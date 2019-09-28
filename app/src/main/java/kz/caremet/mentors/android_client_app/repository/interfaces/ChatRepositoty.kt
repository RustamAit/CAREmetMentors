package kz.caremet.mentors.android_client_app.repository.interfaces

import io.reactivex.Observable
import io.reactivex.Single
import kz.caremet.mentors.android_client_app.core.data.DataEntities

interface ChatRepositoty {
    fun getChatRooms(): Single<List<DataEntities.ChatRoomFromApi>>
    fun getChatRoomsFromDB(): Observable<List<DataEntities.ChatRoomFromDb>>
    fun getChatRoomMessagesInDb(chatRoomId: Int): Observable<List<DataEntities.MessageFormDb>>
}