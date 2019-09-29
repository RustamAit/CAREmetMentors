package kz.caremet.mentors.android_client_app.repository.sharedPreferences

import android.content.SharedPreferences

class LocalSharedPrefImpl(private val pref: SharedPreferences): LocalSharedPref{

    override fun getCurrentMentorId(): String? {
        return pref.getString("mentorIdString", null)
    }

    override fun setCurrentMentorId(mentorId: String) {
        pref.edit().putString("mentorIdString", mentorId).apply()
    }

    override fun setCurrentRealMentorId(mentorId: Int) {
        pref.edit().putInt("mentorId", mentorId).apply()
    }

    override fun getCurrentRealMentorId(): Int {
        return pref.getInt("mentorId", 0)
    }

    override fun getCurrentMentorName(): String {
        return pref.getString("mentorName","Неизветный чувак")!!
    }

    override fun setCurrentMentorName(mentorName: String) {
        pref.edit().putString("mentorName", mentorName).apply()
    }

    override fun clearAllData() {
        pref.edit().clear().commit()
    }


    override fun getAccessToken(): String? {
        return pref.getString("accessToken", "toookeen")
    }

    override fun setAccessToken(token: String) {
        pref.edit().putString("accessToken", "Bearer $token").commit()
    }



}