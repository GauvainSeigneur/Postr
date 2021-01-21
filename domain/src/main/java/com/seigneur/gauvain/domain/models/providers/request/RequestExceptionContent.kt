package com.seigneur.gauvain.domain.models.providers.request

data class RequestExceptionContent(
    val exceptionType: RequestExceptionType,
    val message: String? = null
)