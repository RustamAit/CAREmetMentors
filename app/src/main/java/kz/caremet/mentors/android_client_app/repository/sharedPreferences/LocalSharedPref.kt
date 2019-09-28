package kz.caremet.mentors.android_client_app.repository.sharedPreferences

interface LocalSharedPref {

    fun setCurrentRealMentorId(mentorId: Int)
    fun getCurrentRealMentorId(): Int
    fun getCurrentMentorId(): String?
    fun setCurrentMentorId(mentorId: String)
    fun setCurrentMentorName(mentorName:String)
    fun getCurrentMentorName(): String
}