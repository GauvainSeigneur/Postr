package com.seigneur.gauvain.data_adapter.models.remote.token

/**
 * Access token from Unsplash service
 */
data class AccessTokenModel(
    val access_token: String,
    val token_type: String,
    val scope: String,
    val created_at: String
)