package kz.caremet.mentors.android_client_app.views.signUp.viewModel

import io.reactivex.Single
import kz.caremet.mentors.android_client_app.core.DataEntities

interface InfoFragmentsViewModel {

    fun signIn(questionaryData: DataEntities.QuestionaryData): Single<DataEntities.Mentor>

}