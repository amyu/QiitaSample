package amyu.presentation.data.extensions

import amyu.presentation.domain.exception.NetworkErrorException
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.HttpException
import java.net.UnknownHostException


fun <T> Single<T>.convertDomainException(): Single<T> =
        onErrorResumeNext {
            Single.error {
                when (it) {
                    is HttpException -> {
                        val errorMessage = try {
                            Gson().fromJson(it.response().errorBody().string(), NetworkErrorMessage::class.java).message
                        } catch (e: JsonParseException) {
                            null
                        }
                        NetworkErrorException(it.code(), errorMessage)
                    }
                    is UnknownHostException -> {
                        NetworkErrorException(0, "ネットワーク通信がありません")
                    }
                    else -> {
                        it
                    }
                }
            }
        }

private class NetworkErrorMessage(@SerializedName("error") val message: String?)