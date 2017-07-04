package amyu.presentation.domain.usecase.login

import amyu.presentation.domain.service.login.LoginService
import amyu.share.SupportTest
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class LoginUseCaseTest : SupportTest {
    @Mock
    lateinit var loginService: LoginService

    lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        InitMocks(this)
        loginUseCase = LoginUseCaseImpl(loginService)
    }

    /**
     * LoginService.loginが正常終了したら､executeも正常終了すること
     */
    @Test
    fun testExecute_ThenReturnCompletable() {
        val id = "hoge"
        val password = "hoge"

        When(loginService.login(id, password)).ThenReturn(Completable.complete())

        try {
            loginUseCase.execute(id, password)
                    .test()
                    .await()
                    .assertComplete()
        } catch (e: InterruptedException) {
            Fail()
        }
    }

}