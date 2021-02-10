package com.seigneur.gauvain.data_adapter.models.remote.photo

import com.seigneur.gauvain.data_adapter.models.remote.user.UserModel

data class PhotoModel(
    val id: String,
    val width: Int,
    val height: Int,
    val color: String?,
    var user: UserModel,
    var urls: PhotoUrlModel,
    var exif: ExifModel?,
    var links: LinksModel,
    var description: String?,
    var liked_by_user: Boolean
)