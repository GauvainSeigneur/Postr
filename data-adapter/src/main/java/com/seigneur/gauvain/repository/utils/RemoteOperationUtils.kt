package com.seigneur.gauvain.repository.utils

import android.util.Log
import com.seigneur.gauvain.domain.models.repository.*
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T> apiCall(call: suspend () -> Response<T>): RepositoryResult<T> {
    val response: Response<T>
    try {
        response = call.invoke()
    } catch (t: Throwable) {
        return RepositoryResult.Error(getRequestExceptionContent(t))
    }

    return if (!response.isSuccessful) {
        val errorBody = response.errorBody()
        Log.d("remoteOperationUtils", "response unsuccessful: $errorBody")
        RepositoryResult.Error(
            RepositoryExceptionContent(
                RepositoryExceptionType.Remote(
                    HttpRequestExceptionType.REQUEST_UNSUCCESSFUL
                ),
                //Gson().fromJson(errorBody.toString() , javaClass) //todo deserialize it
                errorBody.toString()
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
            RepositoryExceptionContent(
                RepositoryExceptionType.Remote(
                    HttpRequestExceptionType.HTTP_EXCEPTION,
                ),
                "${throwable.cause}: ${throwable.message}"
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

