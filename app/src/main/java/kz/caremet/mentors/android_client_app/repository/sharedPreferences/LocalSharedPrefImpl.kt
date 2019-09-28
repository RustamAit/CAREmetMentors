package kz.caremet.mentors.android_client_app.repository.sharedPreferences

import android.content.SharedPreferences

class LocalSharedPrefImpl(private val pref: SharedPreferences): LocalSharedPref{
    override fun setCurrentMentorId(mentorId: Int) {
        pref.edit().putInt("mentorId", mentorId).apply()
    }

    override fun getCurrentMentorId(): Int {
        return pref.getInt("mentorId", 0)
    }

}