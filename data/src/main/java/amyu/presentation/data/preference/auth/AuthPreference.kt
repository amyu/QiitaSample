package amyu.presentation.data.preference.auth

import amyu.presentation.data.preference.Preference
import io.reactivex.Completable
import io.reactivex.Single


interface AuthPreference : Preference {
    fun getToken(): Single<String>

    fun setToken(token: String): Completable
}