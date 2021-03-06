package com.seigneur.gauvain.data_adapter.models.remote.photo

data class PhotoUrl(
    //Fallback in case of is null,
    var thumb: String,
    var small: String? = thumb,
    var regular: String? = small,
    var full: String? = regular,
    var raw: String? = full
)