package com.seigneur.gauvain.repository.models.remote.user

data class ProfileImage(
    var small: String,
    var medium: String? = small,
    var large: String? = medium
)