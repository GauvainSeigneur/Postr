package com.seigneur.gauvain.domain.models.providers

import com.seigneur.gauvain.domain.models.providers.request.RequestExceptionType

abstract class BaseProviderException(
    val type: RequestExceptionType,
    val description: String?
) : Exception()