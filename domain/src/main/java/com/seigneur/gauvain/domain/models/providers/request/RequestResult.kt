package com.seigneur.gauvain.domain.models.providers.request

sealed class RequestResult<out T> {
    data class Success<out T>(val data: T) : RequestResult<T>()
    data class Error(val exceptionContent: RequestExceptionContent) : RequestResult<Nothing>()
}