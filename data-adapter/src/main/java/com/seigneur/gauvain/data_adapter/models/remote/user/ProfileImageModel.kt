package com.seigneur.gauvain.data_adapter.models.remote.user

data class ProfileImageModel(
    var small: String,
    var medium: String? = small,
    var large: String? = medium
)