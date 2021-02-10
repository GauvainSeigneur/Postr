package com.seigneur.gauvain.domain.models

data class Photo(
    val id: String,
    val description: String?,
    val photoUrls: PhotoUrls,
    val width: Int,
    val height: Int,
    val color: String?
)

data class PhotoUrls(
    var thumb: String,
    var small: String,
    var regular: String,
    var full: String,
    var raw: String
)

