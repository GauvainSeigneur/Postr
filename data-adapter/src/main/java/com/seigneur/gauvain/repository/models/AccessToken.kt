package com.seigneur.gauvain.repository.models

import com.google.gson.annotations.SerializedName

/**
 * Access token from Unsplash service
 */
data class AccessToken(
    @SerializedName("errors")
    override val errors: List<String>?,
    @SerializedName("access_token")
    val access_token: String,
    @SerializedName("token_type")
    val token_type: String,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("created_at")
    val created_at: String
) : BaseResponse()