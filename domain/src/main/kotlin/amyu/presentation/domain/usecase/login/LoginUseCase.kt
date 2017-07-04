package amyu.presentation.domain.usecase.login

import io.reactivex.Completable


interface LoginUseCase {
    fun execute(id: String, password: String): Completable
}