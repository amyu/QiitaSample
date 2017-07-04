package amyu.presentation.data.api

import amyu.presentation.data.preference.auth.AuthPreference
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


//TODO Flavorごとに分けたい
class OkHttpClientFactory(authPreference: AuthPreference) {
    private val authInterceptor = AuthInterceptor(authPreference)

    fun create(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(authInterceptor)
        addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }.build()

    private class AuthInterceptor(private val authPreference: AuthPreference) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val token = authPreference.getToken().blockingGet()
            val originalRequest = chain.request()
            val newUrl = originalRequest.url().newBuilder().apply {
                addQueryParameter("token", token)
            }.build()
            val newRequest = chain.request().newBuilder().apply {
                url(newUrl)
            }.build()
            return chain.proceed(newRequest)
        }
    }
}