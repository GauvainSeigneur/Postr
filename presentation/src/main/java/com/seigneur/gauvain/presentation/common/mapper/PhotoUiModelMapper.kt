package com.seigneur.gauvain.presentation.common.mapper

import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.presentation.common.model.PhotoDimensions
import com.seigneur.gauvain.presentation.common.provider.StringProvider
import com.seigneur.gauvain.presentation.common.model.PhotoUiModel
import com.seigneur.gauvain.presentation.common.model.PhotoUiUrl

class PhotoUiModelMapper(private val stringProvider: StringProvider) {

    fun toPhotoUiModel(photo: Photo): PhotoUiModel =
        PhotoUiModel(
            photo.id,
            photo.description ?: stringProvider.noDescriptionProvided,
            PhotoUiUrl(
                photo.photoUrls.thumb,
                photo.photoUrls.small,
                photo.photoUrls.regular,
                photo.photoUrls.full,
                photo.photoUrls.raw,
            ),
            PhotoDimensions(
                photo.width,
                photo.height
            ),
            photo.color?:"F3F3F3"
        )

}