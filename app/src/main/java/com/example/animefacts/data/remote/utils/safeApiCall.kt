package com.example.animefacts.data.remote.utils

import com.example.animefacts.data.common.ApiResult
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ApiResult<T> {
    return try {

        ApiResult.Success(apiCall())

    } catch (e: IOException) {

        ApiResult.NetworkError("No internet connection")

    } catch (e: HttpException) {

        ApiResult.ServerError(e.code(), e.message()) //404

    } catch (e: Exception) {

        ApiResult.UnknownError(e.message ?: "Unknown error") //500
    }
}