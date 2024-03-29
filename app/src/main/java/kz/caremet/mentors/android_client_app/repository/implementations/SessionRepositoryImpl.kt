package kz.caremet.mentors.android_client_app.repository.implementations

import android.util.Log
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.repository.dao.MentorDao
import kz.caremet.mentors.android_client_app.repository.interfaces.SessionRepository
import kz.caremet.mentors.android_client_app.repository.services.LoginService
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref

class SessionRepositoryImpl(private val sharedPref: LocalSharedPref,private val loginService: LoginService,private val mentorDao: MentorDao): SessionRepository {
    override fun setCurrentMentorId(mentorId: Int) {
        sharedPref.setCurrentRealMentorId(mentorId)
    }

    override fun getCurrentMentorId(): Int {
        return sharedPref.getCurrentRealMentorId()
    }

    override fun signUp(questionaryData: DataEntities.QuestionaryData): Single<DataEntities.Mentor> {
        return loginService.signUp(questionaryData)
            .subscribeOn(Schedulers.io())
            .map {
                mentorDao.upsertDeal(it)
                Log.d("MENTOR_TEST", it.toString())
                it

                //testsi@gmail.com
            }
    }

    override fun signIn(signInData: DataEntities.SignInData): Single<DataEntities.SignUpData>{
        return loginService.signIn(signInData).subscribeOn(Schedulers.io())
            .map {
                setCurrentMentorId(it.real_id)
                sharedPref.setCurrentMentorId(it.id)
                sharedPref.setAccessToken(it.access_token)
                Log.d("TEEEST",it.access_token)
                it
            }
    }
}