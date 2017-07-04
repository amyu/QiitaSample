package amyu.presentation.data.api.login

import amyu.presentation.data.api.login.request.LoginRequest
import amyu.presentation.data.api.login.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import kotlin.Any as NoContent

interface LoginApi {
    @POST("/api/v1/auth")
    fun login(@Body request: LoginRequest): Single<LoginResponse>
}