package kz.caremet.mentors.android_client_app.repository.services

import io.reactivex.Single
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import retrofit2.http.*

interface ChatRoomService {

    @GET("mentors/{mentorId}/chat_rooms/")
    fun getChatRooms(@Header("Authorization") token: String?
                     ,@Path("mentorId") mentorId: Int): Single<List<DataEntities.ChatRoomFromApi>>


    @POST("mentors/{mentorId}/chat_rooms/{chat_room_id}")
    fun postMessage(@Header("Authorization") token: String?, @Path("mentorId") mentorId: Int, @Path("chat_room_id") chatRoomId: Int, @Body message: DataEntities.MessageForPost): Single<DataEntities.MessageFromApi>

    @GET("mentors/{mentorId}/events/")
    fun getEvents(@Header("Authorization") token: String?
                     ,@Path("mentorId") mentorId: Int): Single<List<DataEntities.Event>>


    @POST("mentors/{mentorId}/events/{event_id}/report")
    fun postReport(@Header("Authorization") token: String?
                   ,@Path("mentorId") mentorId: Int,@Path("event_id") eventId: Int,@Body report: DataEntities.ReportForPost): Single<DataEntities.Report>

    @GET("mentors/{mentorId}/reports/")
    fun getReports(@Path("mentorId") mentorId: Int): Single<List<DataEntities.Report>>
}