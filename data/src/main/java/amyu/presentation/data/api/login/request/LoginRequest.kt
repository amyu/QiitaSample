package amyu.presentation.data.api.login.request

import com.google.gson.annotations.SerializedName


data class LoginRequest(
        @SerializedName("url_name") val id: String,
        @SerializedName("password") val password: String
)