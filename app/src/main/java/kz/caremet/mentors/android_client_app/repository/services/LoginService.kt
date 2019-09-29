package kz.caremet.mentors.android_client_app.repository.services

import io.reactivex.Single
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService {

    @POST("sign_up")
    fun signUp(@Body questionaryData: DataEntities.QuestionaryData): Single<DataEntities.Mentor>

    @POST("sign_in")
    fun signIn(@Body signInData: DataEntities.SignInData): Single<DataEntities.SignUpData>


    @GET("cities")
    fun getCities(): Single<List<DataEntities.City>>

}