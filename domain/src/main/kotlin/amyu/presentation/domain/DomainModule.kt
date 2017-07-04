package amyu.presentation.domain

import amyu.presentation.domain.usecase.login.LoginUseCase
import amyu.presentation.domain.usecase.login.LoginUseCaseImpl
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider


val domainModule = Kodein.Module {
    bind<LoginUseCase>() with provider { LoginUseCaseImpl(instance()) }
}