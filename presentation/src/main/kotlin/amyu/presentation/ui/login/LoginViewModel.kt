package amyu.presentation.ui.login

import amyu.presentation.BR
import amyu.presentation.R
import amyu.presentation.ui.ViewModel
import android.databinding.Bindable

class LoginViewModel(
        val onLoginClickListener: () -> Unit
) : ViewModel() {
    val title = R.string.login___toolbar_title

    @Bindable
    var name: String = ""
        set(value) {
            field = value
            onUserInputChanged()
        }

    @Bindable
    var password: String = ""
        set(value) {
            field = value
            onUserInputChanged()
        }

    @Bindable
    var enableLoginButton: Boolean = false


    private fun onUserInputChanged() {
        enableLoginButton = name.isNotEmpty() && password.isNotEmpty()
        notifyPropertyChanged(BR.enableLoginButton)
    }
}