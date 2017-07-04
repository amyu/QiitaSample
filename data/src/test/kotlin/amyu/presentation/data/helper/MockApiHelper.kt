package amyu.presentation.data.helper

import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response


object MockApiHelper {
    fun <T> createCustomErrorResponse(code: Int, jsonStr: String): Response<T> = Response.error(code, ResponseBody.create(MediaType.parse("application/json"), jsonStr))
}