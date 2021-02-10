package com.seigneur.gauvain.data_adapter.mapper

import com.seigneur.gauvain.data_adapter.models.remote.photo.PhotoModel
import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.PhotoUrls

class PhotoMapper {

    fun toPhoto(photoModel: PhotoModel): Photo =
        Photo(
            photoModel.id,
            photoModel.description,
            PhotoUrls(
                photoModel.urls.thumb,
                photoModel.urls.small,
                photoModel.urls.regular,
                photoModel.urls.full,
                photoModel.urls.raw
            ),
            photoModel.width,
            photoModel.height,
            photoModel.color
        )

}