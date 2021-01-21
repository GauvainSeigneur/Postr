package com.seigneur.gauvain.domain.models

import com.seigneur.gauvain.domain.models.providers.request.RequestExceptionContent

sealed class OutCome<out T> {
    data class Success<out T>(val data: T) : OutCome<T>()
    data class Error(val exceptionContent: RequestExceptionContent) : OutCome<Nothing>()
}