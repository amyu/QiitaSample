package amyu.presentation.data.extensions

import amyu.presentation.data.helper.MockApiHelper
import amyu.share.SupportTest
import amyu.presentation.domain.exception.NetworkErrorException
import io.reactivex.Single
import org.junit.Test
import retrofit2.HttpException

class SingleExtensionsTest : SupportTest {

    /**
     * HttpExceptionがNetworkErrorExceptionに変換されていること
     */
    @Test
    fun testConvertDomainException() {
        val errorMessage = "hoge"
        val errorCode = 403
        val response = MockApiHelper.createCustomErrorResponse<Any>(errorCode, """{ "error": "$errorMessage" }""")
        try {
            Single.error<Any>(HttpException(response))
                    .convertDomainException()
                    .toCompletable()
                    .test()
                    .assertError(NetworkErrorException(errorCode, errorMessage))
        } catch (e: Throwable) {
            Fail()
        }
    }
}