package amyu.presentation.data.api.login.response

import com.google.gson.annotations.SerializedName


data class LoginResponse(
        @SerializedName("url_name") val userName: String,
        @SerializedName("token") val token: String
)