package com.seigneur.gauvain.domain.repository

import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.PhotoCollection
import com.seigneur.gauvain.domain.models.User
import com.seigneur.gauvain.domain.models.repository.BaseRepositoryException
import com.seigneur.gauvain.domain.models.repository.RepositoryExceptionType
import kotlin.jvm.Throws

interface SearchRepository {
    @Throws(SearchException::class)
    suspend fun searchUser(query: String, page: Long, perPage: Int): List<User>

    @Throws(SearchException::class)
    suspend fun searchPhoto(query: String, page: Long, perPage: Int): List<Photo>

    @Throws(SearchException::class)
    suspend fun searchCollection(query: String, page: Long, perPage: Int): List<PhotoCollection>
}

class SearchException(
    typeHttp: RepositoryExceptionType,
    description: String?
) : BaseRepositoryException(typeHttp, description)


