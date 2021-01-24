package com.seigneur.gauvain.domain.models.repository


sealed class RepositoryExceptionType {
    data class Remote(val type: HttpRequestExceptionType) : RepositoryExceptionType()
    object Local : RepositoryExceptionType()
}