package kz.caremet.mentors.android_client_app.views.MainPage

interface MainPageFragmentListener {
    fun startChatActivity(chatRoomId: Int)
    fun startReportCreateActivity(eventId: Int, eventTitle: String)
}