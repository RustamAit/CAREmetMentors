package kz.caremet.mentors.android_client_app.core.data

data class WebSocketMessage(
    val identifier: String,
    val message: Message?
)

data class Message(
    val web_socket_data: WebSocketData?
)

data class WebSocketData(
      val chat_rooms: List<DataEntities.ChatRoomFromApi>?,
      val events: List<DataEntities.Event>?,
      val messages: List<DataEntities.MessageFromApi>?
)