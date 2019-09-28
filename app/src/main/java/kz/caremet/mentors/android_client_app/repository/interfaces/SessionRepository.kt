package kz.caremet.mentors.android_client_app.repository.interfaces

import io.reactivex.Single
import kz.caremet.mentors.android_client_app.core.data.DataEntities

interface SessionRepository {

    fun setCurrentMentorId(mentorId: Int)
    fun getCurrentMentorId(): Int
    fun signUp(questionaryData: DataEntities.QuestionaryData): Single<DataEntities.Mentor>
    fun signIn(signInData: DataEntities.SignInData): Single<DataEntities.Mentor>
}