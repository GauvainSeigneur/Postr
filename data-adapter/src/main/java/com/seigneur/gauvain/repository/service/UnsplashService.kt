package com.seigneur.gauvain.repository.service

import com.seigneur.gauvain.repository.models.remote.photo.Photo
import com.seigneur.gauvain.repository.models.remote.token.AccessToken
import retrofit2.Response
import retrofit2.http.GET
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


    /**
     * Get paged list of photos
     */
    @GET("photos")
    suspend fun photos(
        @Query("page") page: Long,
        @Query("per_page") pagePage: Int,
        @Query("order_by") value: String?
    ): Response<List<Photo>>

}