package com.seigneur.gauvain.data_adapter.models.remote.photo

import com.seigneur.gauvain.data_adapter.models.remote.user.User

data class Photo(
    val id: String,
    val width: Int,
    val height: Int,
    val color: String?,
    var user: User,
    var urls: PhotoUrl,
    var exif: Exif?,
    var links: Links,
    var description: String?,
    var liked_by_user: Boolean
)