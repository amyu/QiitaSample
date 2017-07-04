package amyu.presentation.ui.login

import amyu.presentation.R
import amyu.presentation.databinding.FragmentLoginBinding
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance


class LoginFragment : Fragment(), LoginContract.View, KodeinInjected {
    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }

    override val injector = KodeinInjector()

    private val presenter: LoginContract.Presenter by instance()

    private lateinit var binding: FragmentLoginBinding

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(appKodein())

        val navigator = requireNotNull(context as? LoginContract.Navigator)
        presenter.setUp(this, navigator)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun onDetach() {
        presenter.onDetach()
        super.onDetach()
    }

    override fun setViewModel(viewModel: LoginViewModel) {
        binding.viewModel = viewModel
    }
}