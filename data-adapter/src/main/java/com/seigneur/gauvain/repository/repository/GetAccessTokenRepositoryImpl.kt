package com.seigneur.gauvain.repository.repository

import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.repository.GetAccessTokenException
import com.seigneur.gauvain.domain.repository.GetAccessTokenRepository
import com.seigneur.gauvain.domain.models.repository.RepositoryResult
import com.seigneur.gauvain.repository.service.*
import com.seigneur.gauvain.repository.utils.apiCall

class GetAccessTokenRepositoryImpl(private val api: UnsplashService) :
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
        return Token("", "")
    }

    companion object {
        private const val GRANT_TYPE = "authorization_code"
    }

}
