package com.seigneur.gauvain.presentation.common.model.livedata

/**
 * Used to encapsulates liveData value
 * Not used for PagedList
 */
sealed class LiveDataState<out T : Any> {
    data class Success<out T : Any>(val data: T) : LiveDataState<T>()
    data class Error(val errorData: ErrorData) : LiveDataState<Nothing>()
}