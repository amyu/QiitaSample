package amyu.presentation.ui.login

import amyu.presentation.domain.usecase.login.LoginUseCase
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class LoginPresenter(
        private val loginUseCase: LoginUseCase,
        private val uiScheduler: Scheduler
) : LoginContract.Presenter {
    private lateinit var view: LoginContract.View
    private lateinit var navigator: LoginContract.Navigator

    val viewModel = LoginViewModel(
            onLoginClickListener = this::onLoginClick
    )

    private val disposables = CompositeDisposable()

    override fun setUp(view: LoginContract.View, navigator: LoginContract.Navigator) {
        this.view = view
        this.navigator = navigator
    }

    override fun onViewCreated() {
        view.setViewModel(viewModel)
    }

    override fun onDetach() {
        disposables.clear()
    }

    fun onLoginClick() {
        loginUseCase.execute(viewModel.name, viewModel.password)
                .observeOn(uiScheduler)
                .subscribe({
                    navigator.moveToList()
                }, {
                    view.showErrorDialog(it)
                }).addTo(disposables)
    }
}