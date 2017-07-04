package amyu.presentation.domain.service.login

import io.reactivex.Completable


interface LoginService {
    fun login(id: String, password: String): Completable
}