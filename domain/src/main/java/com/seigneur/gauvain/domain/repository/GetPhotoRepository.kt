package com.seigneur.gauvain.domain.repository

import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.repository.BaseRepositoryException
import com.seigneur.gauvain.domain.models.repository.RepositoryExceptionType
import kotlin.jvm.Throws

interface GetPhotoRepository {
    @Throws(GetPhotoException::class)
    suspend fun getPhotos(page: Long, perPage: Int, order_by: String?): List<Photo>
}

class GetPhotoException(
    typeHttp: RepositoryExceptionType,
    description: String?
) : BaseRepositoryException(typeHttp, description)


