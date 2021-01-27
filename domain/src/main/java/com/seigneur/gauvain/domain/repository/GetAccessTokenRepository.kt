package com.seigneur.gauvain.domain.repository

import com.seigneur.gauvain.domain.models.repository.BaseRepositoryException
import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.models.repository.RepositoryExceptionType
import kotlin.jvm.Throws

interface GetAccessTokenRepository {
    @Throws(GetAccessTokenException::class)
    suspend fun getAccessToken(code: String): Token

    @Throws(GetAccessTokenException::class)
    suspend fun getLocalToken(): Token
}

class GetAccessTokenException(
    type: RepositoryExceptionType,
    description: String?
) : BaseRepositoryException(type, description)


