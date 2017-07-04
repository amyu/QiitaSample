package amyu.presentation.data.service.login

import amyu.presentation.data.api.login.LoginApi
import amyu.presentation.data.api.login.request.LoginRequest
import amyu.presentation.data.api.login.response.LoginResponse
import amyu.presentation.data.preference.auth.AuthPreference
import amyu.presentation.domain.service.login.LoginService
import amyu.share.SupportTest
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class LoginServiceTest : SupportTest {
    @Mock
    lateinit var loginApi: LoginApi

    @Mock
    lateinit var authPreference: AuthPreference

    lateinit var loginService: LoginService

    @Before
    fun setUp() {
        InitMocks(this)
        loginService = LoginServiceImpl(loginApi, authPreference)
    }

    /**
     * ログインに成功し､Tokenを正常に取得完了後､内部に保存すること
     */
    @Test
    fun testLogin_ThenReturnSuccess() {
        val id = "id"
        val password = "password"
        val token = "token"
        val loginRequest = LoginRequest(id, password)
        When(loginApi.login(loginRequest)).ThenReturn(Single.just(LoginResponse(id, token)))

        try {
            loginService.login(id, password)
                    .test()
                    .await()
                    .assertComplete()
            Verify(authPreference).setToken(token)
        } catch (e: InterruptedException) {
            Fail()
        }
    }
}