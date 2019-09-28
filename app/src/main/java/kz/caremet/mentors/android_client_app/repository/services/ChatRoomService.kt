package kz.caremet.mentors.android_client_app.repository.services

import io.reactivex.Single
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatRoomService {

    @GET("mentors/{mentorId}/chat_rooms/view")
    fun getChatRooms(@Path("mentorId") mentorId: Int): Single<List<DataEntities.ChatRoomFromApi>>


    @POST("mentors/{mentorId}/chat_rooms/view")
    fun postMessage(@Body message: DataEntities.MessageForPost): Single<DataEntities.MessageFromApi>


}