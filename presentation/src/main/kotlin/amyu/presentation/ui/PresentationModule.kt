package amyu.presentation.ui

import amyu.presentation.ui.login.LoginContract
import amyu.presentation.ui.login.LoginPresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider


val presentationModule = Kodein.Module {
    bind<LoginContract.Presenter>() with provider { LoginPresenter(instance(), instance("ui")) }
}