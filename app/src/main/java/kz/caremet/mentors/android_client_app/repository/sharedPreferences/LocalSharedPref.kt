package kz.caremet.mentors.android_client_app.repository.sharedPreferences

interface LocalSharedPref {

    fun setCurrentMentorId(mentorId: Int)
    fun getCurrentMentorId(): Int

}