package com.seigneur.gauvain.repository.repository

import com.seigneur.gauvain.repository.service.*

class GetAccessTokenRepository(private val api: UnsplashService) {

    suspend fun getAccessToken(code: String) = api.getAccessToken(
        TOKEN_URL_REQUEST,
        CLIENT_ID_VALUE,
        CLIENT_SECRET_KEY,
        AUTH_REDIRECT_URI,
        code,
        GRANT_TYPE_VALUE
    )

}
