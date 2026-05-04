package com.example.animefacts.util

import android.content.Context
import com.example.animefacts.R
import com.example.animefacts.data.common.ApiResult

fun mapErrorToMessage(result: ApiResult<*>, context: Context): String {
    return when (result) {
        is ApiResult.NetworkError -> context.getString(R.string.error_network)
        is ApiResult.ServerError -> context.getString(R.string.error_server, result.code, result.message)
        is ApiResult.UnknownError -> context.getString(R.string.error_unknown, result.message)
        else -> context.getString(R.string.error_something_went_wrong)
    }
}