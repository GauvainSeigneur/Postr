package com.seigneur.gauvain.domain.repository

import com.seigneur.gauvain.domain.models.repository.BaseRepositoryException
import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.models.repository.HttpRequestExceptionType
import com.seigneur.gauvain.domain.models.repository.RepositoryExceptionType
import kotlin.jvm.Throws

interface InsertTokenRepository {
    @Throws(InsertTokenException::class)
    suspend fun saveToken(token: Token): Long
}

class InsertTokenException(
    typeHttp: RepositoryExceptionType,
    description: String?
) : BaseRepositoryException(typeHttp, description)


