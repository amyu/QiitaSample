package amyu.presentation.domain.usecase.login

import amyu.presentation.domain.service.login.LoginService
import io.reactivex.Completable


class LoginUseCaseImpl(
        private val loginService: LoginService
) : LoginUseCase {
    override fun execute(id: String, password: String): Completable = loginService.login(id, password)
}