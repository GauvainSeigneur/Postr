package com.seigneur.gauvain.repository.repository

import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.models.repository.RepositoryExceptionType
import com.seigneur.gauvain.domain.repository.GetAccessTokenException
import com.seigneur.gauvain.domain.repository.GetAccessTokenRepository
import com.seigneur.gauvain.domain.models.repository.RepositoryResult
import com.seigneur.gauvain.repository.local.PostrDataBase
import com.seigneur.gauvain.repository.service.*
import com.seigneur.gauvain.repository.utils.apiCall
import com.seigneur.gauvain.repository.utils.performRoomCall

class GetAccessTokenRepositoryImpl(
    private val api: UnsplashService,
    private val dataBase: PostrDataBase
) :
    GetAccessTokenRepository {

    override suspend fun getAccessToken(code: String): Token {
        return when (val result = apiCall {
            api.getAccessToken(
                TOKEN_URL_REQUEST,
                CLIENT_ID_VALUE,
                CLIENT_SECRET_KEY,
                AUTH_REDIRECT_URI,
                code,
                GRANT_TYPE
            )
        }) {
            is RepositoryResult.Success ->
                Token(
                    date = result.data.created_at,
                    value = result.data.access_token
                )
            is RepositoryResult.Error -> throw GetAccessTokenException(
                result.errorContent.type,
                result.errorContent.message
            )
        }
    }

    override suspend fun getLocalToken(): Token {
        return when (val result = performRoomCall {
            dataBase.tokenDao().getTokenEntity()
        }) {
            is RepositoryResult.Success -> {
                result.data?.let {
                    Token(
                        it.date,
                        it.value
                    )
                } ?: throw GetAccessTokenException(
                    type = RepositoryExceptionType.Local,
                    description = "token identity not found in database"
                )
            }
            is RepositoryResult.Error ->
                throw GetAccessTokenException(
                    result.errorContent.type,
                    result.errorContent.message
                )
        }
    }

    companion object {
        private const val GRANT_TYPE = "authorization_code"
    }

}
