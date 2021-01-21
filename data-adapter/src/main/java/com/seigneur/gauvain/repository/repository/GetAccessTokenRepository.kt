package com.seigneur.gauvain.repository.repository

import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.providers.GetAccessTokenException
import com.seigneur.gauvain.domain.providers.GetAccessTokenProvider
import com.seigneur.gauvain.domain.models.providers.request.RequestResult
import com.seigneur.gauvain.repository.service.*
import com.seigneur.gauvain.repository.utils.apiCall

class GetAccessTokenRepository(private val api: UnsplashService) :
    GetAccessTokenProvider {

    override suspend fun getAccessToken(code: String): Token {
        return when (val result = apiCall {
            api.getAccessToken(
                TOKEN_URL_REQUEST,
                CLIENT_ID_VALUE,
                CLIENT_SECRET_KEY,
                AUTH_REDIRECT_URI,
                code,
                GRANT_TYPE_VALUE
            )
        }) {
            is RequestResult.Success ->
                Token(
                    date = result.data.created_at,
                    value = result.data.access_token
                )
            is RequestResult.Error -> throw GetAccessTokenException(
                result.exceptionContent.exceptionType,
                result.exceptionContent.message
            )
        }
    }

}
