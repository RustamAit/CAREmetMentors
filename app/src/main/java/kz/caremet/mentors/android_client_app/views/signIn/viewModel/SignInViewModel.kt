package kz.caremet.mentors.android_client_app.views.signIn.viewModel

import io.reactivex.Single
import kz.caremet.mentors.android_client_app.core.data.DataEntities

interface SignInViewModel {

    fun signIn(singInData: DataEntities.SignInData): Single<DataEntities.SignUpData>
}