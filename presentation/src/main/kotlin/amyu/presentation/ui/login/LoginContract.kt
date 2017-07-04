package amyu.presentation.ui.login

import amyu.presentation.ui.BasePresenter
import amyu.presentation.ui.BaseView
import amyu.presentation.ui.ErrorDialogView


interface LoginContract {
    interface Presenter : BasePresenter {
        fun setUp(view: View, navigator: Navigator)
        fun onViewCreated()
        fun onDetach()
    }

    interface View : BaseView, ErrorDialogView {
        fun setViewModel(viewModel: LoginViewModel)
    }

    interface Navigator {
        fun moveToList()
    }
}