package com.seigneur.gauvain.repository.models.remote.token

/**
 * Access token from Unsplash service
 */
data class AccessToken(
    val access_token: String,
    val token_type: String,
    val scope: String,
    val created_at: String
)