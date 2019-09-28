package kz.caremet.mentors.android_client_app.views.signUp.viewModel

import io.reactivex.Single
import kz.caremet.mentors.android_client_app.core.DataEntities
import kz.caremet.mentors.android_client_app.repository.interfaces.SessionRepository

class InfoFragmentViewModelImpl(private val repository: SessionRepository): InfoFragmentsViewModel{

    override fun signIn(questionaryData: DataEntities.QuestionaryData): Single<DataEntities.Mentor> =
        repository.signUp(questionaryData).onErrorReturn {
            DataEntities.Mentor(
                0,
                null,null,null,null,null,null
            )
        }

}