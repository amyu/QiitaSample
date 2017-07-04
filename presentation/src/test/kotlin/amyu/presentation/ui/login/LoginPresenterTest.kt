package amyu.presentation.ui.login

import amyu.share.SupportTest
import amyu.presentation.domain.usecase.login.LoginUseCase
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock


class LoginPresenterTest : SupportTest {
    @Mock
    lateinit var loginUseCase: LoginUseCase

    @Mock
    lateinit var view: LoginContract.View

    @Mock
    lateinit var navigator: LoginContract.Navigator

    lateinit var presenter: LoginPresenter

    @Before
    fun setUp() {
        InitMocks(this)
        presenter = LoginPresenter(
                loginUseCase,
                Schedulers.trampoline()
        ).apply {
            setUp(view, navigator)
        }
    }

    /**
     * リスト画面に遷移すること
     */
    @Test
    fun testOnLoginClick_ThenMoveToList() {
        When(loginUseCase.execute(presenter.viewModel.name, presenter.viewModel.password)).ThenReturn(Completable.complete())

        presenter.onViewCreated()
        presenter.onLoginClick()

        Verify(navigator).moveToList()
    }

    /**
     * エラーダイアログが表示されること
     */
    @Test
    fun testOnLoginClick_ThenShowErrorDialog() {
        val exception = RuntimeException()
        When(loginUseCase.execute(presenter.viewModel.name, presenter.viewModel.password)).ThenReturn(Completable.error(exception))

        presenter.onViewCreated()
        presenter.onLoginClick()

        Verify(view).showErrorDialog(exception)
    }
}