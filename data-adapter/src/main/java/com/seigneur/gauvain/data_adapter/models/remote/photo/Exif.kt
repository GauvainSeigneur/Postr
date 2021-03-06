package com.seigneur.gauvain.data_adapter.models.remote.photo

data class Exif(
    val make: String?,
    val model: String?,
    val exposure_time: Long?,
    val aperture: Long?,
    val focal_length: Int?,
    val iso: Int?
)