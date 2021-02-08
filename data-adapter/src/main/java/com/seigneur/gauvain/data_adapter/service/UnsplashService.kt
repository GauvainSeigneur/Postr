package com.seigneur.gauvain.data_adapter.service

import com.seigneur.gauvain.data_adapter.models.remote.collection.PhotoCollection
import com.seigneur.gauvain.data_adapter.models.remote.photo.Photo
import com.seigneur.gauvain.data_adapter.models.remote.search.SearchResponse
import com.seigneur.gauvain.data_adapter.models.remote.token.AccessToken
import com.seigneur.gauvain.data_adapter.models.remote.user.User
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


    @GET("search/users")
    suspend fun searchUser(
        @Query("query") query: String?,
        @Query("page") page: Long,
        @Query("per_page") pagePage: Int
    ): Response<SearchResponse<User>>

    @GET("search/photos")
    suspend fun searchPhoto(
        @Query("query") query: String?,
        @Query("page") page: Long,
        @Query("per_page") pagePage: Int
    ): Response<SearchResponse<Photo>>

    @GET("search/collections")
    suspend fun searchCollection(
        @Query("query") query: String?,
        @Query("page") page: Long,
        @Query("per_page") pagePage: Int
    ): Response<SearchResponse<PhotoCollection>>

}