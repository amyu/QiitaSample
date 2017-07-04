package amyu.presentation.ui.login


import amyu.share.SupportTest
import org.junit.Before
import org.junit.Test

class LoginViewModelTest : SupportTest {
    lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel({})
    }

    /**
     * nameとpasswordが各1文字以上のときLoginButtonがEnableのこと
     */
    @Test
    fun testOnUserInputChanged_ThenLoginButtonIsEnable() {
        loginViewModel.name = "h"
        loginViewModel.password = "f"

        AssertTrue(loginViewModel.enableLoginButton)
    }

    /**
     * nameとpasswordのどちらか､両方かが0文字のときまたはLoginButtonがDisableのこと
     */
    @Test
    fun testOnUserInputChanged_ThenLoginButtonIsDisable() {
        loginViewModel.name = ""
        loginViewModel.password = "f"

        AssertFalse(loginViewModel.enableLoginButton)

        loginViewModel.name = "h"
        loginViewModel.password = ""

        AssertFalse(loginViewModel.enableLoginButton)

        loginViewModel.name = ""
        loginViewModel.password = ""

        AssertFalse(loginViewModel.enableLoginButton)
    }
}