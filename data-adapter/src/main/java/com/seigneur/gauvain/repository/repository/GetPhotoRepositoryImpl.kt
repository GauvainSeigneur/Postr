package com.seigneur.gauvain.repository.repository

import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.models.repository.RepositoryExceptionType
import com.seigneur.gauvain.domain.repository.GetAccessTokenException
import com.seigneur.gauvain.domain.repository.GetAccessTokenRepository
import com.seigneur.gauvain.domain.models.repository.RepositoryResult
import com.seigneur.gauvain.domain.repository.GetPhotoException
import com.seigneur.gauvain.domain.repository.GetPhotoRepository
import com.seigneur.gauvain.repository.local.PostrDataBase
import com.seigneur.gauvain.repository.service.*
import com.seigneur.gauvain.repository.utils.apiCall
import com.seigneur.gauvain.repository.utils.performRoomCall

class GetPhotoRepositoryImpl(
    private val api: UnsplashService
) : GetPhotoRepository {

    override suspend fun getPhotos(page: Long, perPage: Int, order_by: String?): List<Photo> {
        return when(val result = apiCall { api.photos(page, perPage, order_by) }) {
            is RepositoryResult.Success -> {
                result.data.map {
                    Photo(it.id, it.description)
                }
            }
            is RepositoryResult.Error -> throw GetPhotoException(
                result.errorContent.type,
                result.errorContent.message
            )
        }
    }
}
