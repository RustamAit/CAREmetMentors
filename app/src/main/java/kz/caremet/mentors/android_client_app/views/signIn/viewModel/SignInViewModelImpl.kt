package kz.caremet.mentors.android_client_app.views.signIn.viewModel

import io.reactivex.Single
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.repository.interfaces.SessionRepository

class SignInViewModelImpl(val repository: SessionRepository): SignInViewModel {

    override fun signIn(singInData: DataEntities.SignInData): Single<DataEntities.SignUpData> = repository.signIn(singInData).onErrorReturn {
        DataEntities.SignUpData(
            "asdasdas",
            "aasd",
            0

        )
    }

}