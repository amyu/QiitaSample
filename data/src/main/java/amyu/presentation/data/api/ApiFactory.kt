package amyu.presentation.data.api

import amyu.presentation.data.BuildConfig
import amyu.presentation.data.api.login.LoginApi
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiFactory(private val okHttpClientFactory: OkHttpClientFactory) {
    private fun retrofit(): Retrofit =
            Retrofit.Builder().apply {
                baseUrl(BuildConfig.API_END_POINT)
                client(okHttpClientFactory.create())
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                addConverterFactory(GsonConverterFactory.create(GsonBuilder().apply {
                    setDateFormat("yyyy-MM-dd HH:mm:ss")
                }.create()))
            }.build()

    private inline fun <reified T> create(): T = retrofit().create(T::class.java)

    fun login(): LoginApi = create()
}