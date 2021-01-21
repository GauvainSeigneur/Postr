package com.seigneur.gauvain.repository.utils

import com.seigneur.gauvain.domain.models.providers.request.*
import com.seigneur.gauvain.repository.models.*
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T : BaseResponse> apiCall(call: suspend () -> Response<T>): RequestResult<T> {
    val response: Response<T>
    try {
        response = call.invoke()
    } catch (t: Throwable) {
        return RequestResult.Error(getRequestExceptionContent(t))
    }

    return if (!response.isSuccessful) {
        val errorBody = response.errorBody()
        RequestResult.Error(
            RequestExceptionContent(
                RequestExceptionType.REQUEST_UNSUCCESSFUL,
                errorBody.toString()
            )
        )
    } else {
        val body = response.body()
        body?.let { responseBody ->
            return if (!responseBody.errors.isNullOrEmpty()) {
                RequestResult.Error(
                    RequestExceptionContent(
                        RequestExceptionType.ERROR_RESPONSE,
                        responseBody.errors?.joinToString()
                    )
                )
            } else {
                RequestResult.Success(responseBody)
            }
        } ?: return RequestResult.Error(
            RequestExceptionContent(
                RequestExceptionType.BODY_NULL,
                EXCEPTION_BODY_NULL_DESC
            )
        )
    }
}

private fun getRequestExceptionContent(throwable: Throwable): RequestExceptionContent =
    when (throwable) {
        is UnknownHostException ->
            RequestExceptionContent(
                RequestExceptionType.UNKNOWN_HOST,
                EXCEPTION_UNKNOWN_HOST_DESC
            )
        is UnknownError -> RequestExceptionContent(
            RequestExceptionType.ERROR_UNKNOWN,
            EXCEPTION_ERROR_UNKNOWN_DESC
        )
        is IOException -> RequestExceptionContent(
            RequestExceptionType.CONNECTION_LOST,
            EXCEPTION_CONNECTION_LOST_DESC
        )
        is HttpException -> RequestExceptionContent(
            RequestExceptionType.HTTP_EXCEPTION,
            "${throwable.cause}: ${throwable.message}"
        )
        else -> {
            RequestExceptionContent(
                RequestExceptionType.UNMAPPED_REQUEST_EXCEPTION,
                "${throwable.cause}: ${throwable.message}"
            )
        }
    }

