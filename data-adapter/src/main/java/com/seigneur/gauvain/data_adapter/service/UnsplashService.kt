package com.seigneur.gauvain.data_adapter.service

import com.seigneur.gauvain.data_adapter.models.remote.collection.PhotoCollectionModel
import com.seigneur.gauvain.data_adapter.models.remote.photo.PhotoModel
import com.seigneur.gauvain.data_adapter.models.remote.search.SearchResponseModel
import com.seigneur.gauvain.data_adapter.models.remote.token.AccessTokenModel
import com.seigneur.gauvain.data_adapter.models.remote.user.UserModel
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
    ): Response<AccessTokenModel>


    /**
     * Get paged list of photos
     */
    @GET("photos")
    suspend fun photos(
        @Query("page") page: Long,
        @Query("per_page") pagePage: Int,
        @Query("order_by") value: String?
    ): Response<List<PhotoModel>>


    @GET("search/users")
    suspend fun searchUser(
        @Query("query") query: String?,
        @Query("page") page: Long,
        @Query("per_page") pagePage: Int
    ): Response<SearchResponseModel<UserModel>>

    @GET("search/photos")
    suspend fun searchPhoto(
        @Query("query") query: String?,
        @Query("page") page: Long,
        @Query("per_page") pagePage: Int
    ): Response<SearchResponseModel<PhotoModel>>

    @GET("search/collections")
    suspend fun searchCollection(
        @Query("query") query: String?,
        @Query("page") page: Long,
        @Query("per_page") pagePage: Int
    ): Response<SearchResponseModel<PhotoCollectionModel>>

}