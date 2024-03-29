package kz.caremet.mentors.android_client_app.core

import android.content.Context
import android.net.ConnectivityManager
import com.tinder.scarlet.Lifecycle
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.ShutdownReason
import com.tinder.scarlet.websocket.okhttp.OkHttpWebSocket
import kz.caremet.mentors.android_client_app.core.utils.AuthorizationInterceptor
import kz.caremet.mentors.android_client_app.core.utils.Logger
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

fun createOkHttpClient(context: Context): OkHttpClient {
    val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Logger.api(message) })
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(object : AuthorizationInterceptor(){
            override fun isInternetAvailable(): Boolean {
                return isInternetAvailable(context)
            }
        })
        .build()
}

inline fun <reified T>createWebSocketService(okHttpClient: OkHttpClient, url: String, lifecycle: Lifecycle): T{
    val protocol = OkHttpWebSocket(
        okHttpClient,
        OkHttpWebSocket.SimpleRequestFactory(
            { Request.Builder().url(url).build() },
            { ShutdownReason.GRACEFUL }
        )
    )

    val configuration = Scarlet.Configuration(
        messageAdapterFactories = listOf(GsonMessageAdapter.Factory()),
        streamAdapterFactories = listOf(RxJava2StreamAdapterFactory()),
        lifecycle = lifecycle
    )
    val scarlet = Scarlet(protocol,configuration)
    return scarlet.create(T::class.java)
}


inline fun <reified T> createService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync()).build()
    return retrofit.create(T::class.java)
}