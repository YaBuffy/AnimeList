package com.example.animefacts.data.common


sealed class ApiResult<out T> {

    data class Success<T>(val data: T) : ApiResult<T>()

    data class NetworkError(
        val message: String
    ) : ApiResult<Nothing>()

    data class ServerError(
        val code: Int,
        val message: String
    ) : ApiResult<Nothing>()

    data class UnknownError(
        val message: String
    ) : ApiResult<Nothing>()
}