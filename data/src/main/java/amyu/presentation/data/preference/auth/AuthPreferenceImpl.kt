package amyu.presentation.data.preference.auth

import android.content.Context
import io.reactivex.Completable
import io.reactivex.Single


class AuthPreferenceImpl(override val context: Context) : AuthPreference {
    override val preferenceName: String = AuthPreferenceImpl::class.java.simpleName

    private object Keys {
        val token = "${AuthPreferenceImpl::class.java.simpleName} + .token"
    }

    override fun getToken(): Single<String> = readStringWithOptional(Keys.token, null)

    override fun setToken(token: String): Completable = write {
        it.putString(Keys.token, token)
    }
}