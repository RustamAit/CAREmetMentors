package kz.caremet.mentors.android_client_app.core.utils

import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.IOException

abstract class AuthorizationInterceptor() : Interceptor, KoinComponent {

    val sharedPref: LocalSharedPref by inject()
    abstract fun isInternetAvailable(): Boolean

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable()){
            val request = chain.request().newBuilder().header("Cache-Control",
                "public, only-if-cached, max-stale=" + 60 * 60 * 24)
                .header("Authorization", "Bearer ${sharedPref.getAccessToken()}").build()
            val response = chain.proceed(request)
            return response
        }
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer").build()
        val response = chain.proceed(request)
        return response
    }

}