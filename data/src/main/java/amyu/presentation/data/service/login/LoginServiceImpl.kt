package amyu.presentation.data.service.login

import amyu.presentation.data.api.login.LoginApi
import amyu.presentation.data.api.login.request.LoginRequest
import amyu.presentation.data.extensions.convertDomainException
import amyu.presentation.data.preference.auth.AuthPreference
import amyu.presentation.domain.service.login.LoginService
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers


class LoginServiceImpl(
        private val loginApi: LoginApi,
        private val authPreference: AuthPreference
) : LoginService {
    override fun login(id: String, password: String): Completable =
            loginApi.login(LoginRequest(id, password))
                    .convertDomainException()
                    .doOnSuccess { (_, token) ->
                        authPreference.setToken(token)
                    }
                    .toCompletable()
                    .subscribeOn(Schedulers.computation())
}