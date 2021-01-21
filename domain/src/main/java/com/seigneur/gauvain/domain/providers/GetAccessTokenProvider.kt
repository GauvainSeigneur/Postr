package com.seigneur.gauvain.domain.providers

import com.seigneur.gauvain.domain.models.providers.BaseProviderException
import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.models.providers.request.RequestExceptionType
import kotlin.jvm.Throws

interface GetAccessTokenProvider {
    @Throws(GetAccessTokenException::class)
    suspend fun getAccessToken(code: String): Token
}

class GetAccessTokenException(
    type: RequestExceptionType,
    description: String?
) : BaseProviderException(type, description)


