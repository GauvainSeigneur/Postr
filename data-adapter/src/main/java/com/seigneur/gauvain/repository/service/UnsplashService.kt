package com.seigneur.gauvain.repository.service

import com.seigneur.gauvain.repository.models.remote.AccessToken
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface UnsplashService {

    /**
     * Oauth2
     */
    @POST
    suspend fun getAccessToken(
        @Url url: String,
        @Query("client_id") clientID: String,
        @Query("client_secret") clientSecret: String,
        @Query("redirect_uri") redirectUri: String,
        @Query("code") code: String,
        @Query("grant_type") grantType: String
    ): Response<AccessToken>



}