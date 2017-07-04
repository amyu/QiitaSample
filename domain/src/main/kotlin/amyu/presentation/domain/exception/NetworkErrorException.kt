package amyu.presentation.domain.exception


data class NetworkErrorException(
        val code: Int,
        val errorMessage: String?
) : Throwable()