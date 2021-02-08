package com.seigneur.gauvain.data_adapter.utils

import com.google.gson.Gson
import com.seigneur.gauvain.data_adapter.models.remote.ErrorResponse
import com.seigneur.gauvain.domain.models.repository.*
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException
import java.nio.charset.Charset

suspend fun <T> apiCall(call: suspend () -> Response<T>): RepositoryResult<T> {
    val response: Response<T>
    try {
        response = call.invoke()
    } catch (t: Throwable) {
        return RepositoryResult.Error(getRequestExceptionContent(t))
    }

    return if (!response.isSuccessful) {

        val errorsResponse = convertErrorBody(response.errorBody())
        RepositoryResult.Error(
            mapUnsuccessfulResponse(
                response.code(),
                errorsResponse?.errors?.joinToString(separator = ",") ?: "unmapped errorBody"
            )
        )
    } else {
        val body = response.body()
        body?.let { responseBody ->
            RepositoryResult.Success(responseBody)
        } ?: return RepositoryResult.Error(
            RepositoryExceptionContent(
                RepositoryExceptionType.Remote(
                    HttpRequestExceptionType.BODY_NULL
                ),
                EXCEPTION_BODY_NULL_DESC
            )
        )
    }
}

private fun getRequestExceptionContent(throwable: Throwable): RepositoryExceptionContent =
    when (throwable) {
        is UnknownHostException ->
            RepositoryExceptionContent(
                RepositoryExceptionType.Remote(
                    HttpRequestExceptionType.UNKNOWN_HOST,
                ),
                EXCEPTION_UNKNOWN_HOST_DESC
            )
        is UnknownError ->
            RepositoryExceptionContent(
                RepositoryExceptionType.Remote(
                    HttpRequestExceptionType.ERROR_UNKNOWN,
                ),
                EXCEPTION_ERROR_UNKNOWN_DESC
            )

        is IOException ->
            RepositoryExceptionContent(
                RepositoryExceptionType.Remote(
                    HttpRequestExceptionType.CONNECTION_LOST,
                ),
                EXCEPTION_CONNECTION_LOST_DESC
            )

        is HttpException ->
            mapUnsuccessfulResponse(
                throwable.code(),
                convertErrorBody(throwable.response()?.errorBody())
                    ?.errors?.joinToString(separator = ",") ?: throwable.message()
            )
        else -> {
            RepositoryExceptionContent(
                RepositoryExceptionType.Remote(
                    HttpRequestExceptionType.UNMAPPED_REQUEST_EXCEPTION,
                ),
                "${throwable.cause}: ${throwable.message}"
            )
        }
    }

private fun mapUnsuccessfulResponse(code: Int, errors: String): RepositoryExceptionContent {
    return when (code) {
        400 -> {
            RepositoryExceptionContent(
                RepositoryExceptionType.Remote(
                    HttpRequestExceptionType.BAD_REQUEST,
                ),
                errors
            )
        }
        401 -> {
            RepositoryExceptionContent(
                RepositoryExceptionType.Remote(
                    HttpRequestExceptionType.UNAUTHORIZED,
                ),
                errors
            )
        }
        403 -> {
            RepositoryExceptionContent(
                RepositoryExceptionType.Remote(
                    HttpRequestExceptionType.FORBIDDEN,
                ),
                errors
            )
        }
        404 -> {
            RepositoryExceptionContent(
                RepositoryExceptionType.Remote(
                    HttpRequestExceptionType.NOT_FOUND,
                ),
                errors
            )
        }
        500, 501, 502, 503 -> {
            RepositoryExceptionContent(
                RepositoryExceptionType.Remote(
                    HttpRequestExceptionType.SERVER_ERROR,
                ),
                errors
            )
        }
        else -> {
            RepositoryExceptionContent(
                RepositoryExceptionType.Remote(
                    HttpRequestExceptionType.ERROR_UNKNOWN,
                ),
                errors
            )
        }
    }
}

private fun convertErrorBody(responseBody: ResponseBody?): ErrorResponse? {
    return try {
        responseBody?.source()?.let {
            val l = it.readString(Charset.defaultCharset())
            Gson().fromJson(l, ErrorResponse::class.java)
        }
    } catch (exception: Exception) {
        null
    }
}
