package com.seigneur.gauvain.domain.models.repository

sealed class RepositoryResult<out T> {
    data class Success<out T>(val data: T) : RepositoryResult<T>()
    data class Error(val errorContent: RepositoryExceptionContent) : RepositoryResult<Nothing>()
}