package amyu.presentation.ui.login

import amyu.presentation.R
import amyu.presentation.extensions.shiftFragmentToContainer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class LoginActivity : AppCompatActivity(), LoginContract.Navigator {
    companion object {
        fun createIntent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        savedInstanceState ?: shiftFragmentToContainer(LoginFragment.newInstance())
    }

    override fun moveToList() {
        //start
    }
}
