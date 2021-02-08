package com.seigneur.gauvain.data_adapter.repository

import com.seigneur.gauvain.domain.models.repository.RepositoryResult
import com.seigneur.gauvain.data_adapter.service.*
import com.seigneur.gauvain.data_adapter.utils.apiCall
import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.PhotoCollection
import com.seigneur.gauvain.domain.models.User
import com.seigneur.gauvain.domain.repository.SearchException
import com.seigneur.gauvain.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val api: UnsplashService
) : SearchRepository {

    override suspend fun searchUser(
        query: String,
        page: Long,
        perPage: Int
    ): List<User> {
        return when (val result = apiCall {
            api.searchUser(
                query, page, perPage
            )
        }) {
            is RepositoryResult.Success -> {
                result.data.results.map {
                    User(it.id, it.username)
                }
            }
            is RepositoryResult.Error -> throw SearchException(
                result.errorContent.type,
                result.errorContent.message
            )
        }
    }

    override suspend fun searchPhoto(query: String, page: Long, perPage: Int): List<Photo> {
        return when (val result = apiCall {
            api.searchPhoto(
                query, page, perPage
            )
        }) {
            is RepositoryResult.Success -> {
                result.data.results.map {
                    Photo(it.id, it.description)
                }
            }
            is RepositoryResult.Error -> throw SearchException(
                result.errorContent.type,
                result.errorContent.message
            )
        }
    }

    override suspend fun searchCollection(query: String, page: Long, perPage: Int): List<PhotoCollection> {
        return when (val result = apiCall {
            api.searchCollection(
                query, page, perPage
            )
        }) {
            is RepositoryResult.Success -> {
                result.data.results.map {
                    PhotoCollection(it.id, it.description)
                }
            }
            is RepositoryResult.Error -> throw SearchException(
                result.errorContent.type,
                result.errorContent.message
            )
        }
    }
}
