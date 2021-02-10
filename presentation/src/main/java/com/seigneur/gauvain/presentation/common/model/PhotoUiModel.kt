package com.seigneur.gauvain.presentation.common.model

data class PhotoUiModel(
    val id: String,
    val description: String,
    val photoUrl: PhotoUiUrl,
    val dimens: PhotoDimensions,
    val color: String
)

data class PhotoUiUrl(
    var thumb: String,
    var small: String,
    var regular: String,
    var full: String,
    var raw: String
)

data class PhotoDimensions(
    val width: Int,
    val height: Int
)
