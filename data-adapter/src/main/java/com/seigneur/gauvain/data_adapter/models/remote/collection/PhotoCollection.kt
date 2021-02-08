package com.seigneur.gauvain.data_adapter.models.remote.collection

import com.seigneur.gauvain.domain.models.Photo

data class PhotoCollection(
    val id: String,
    var username: String?,
    var cover_photo: Photo?,
    val title: String?,
    val description: String?,
    val curated: Boolean? = false
) 