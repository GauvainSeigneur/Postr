package com.seigneur.gauvain.presentation.home

import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.presentation.common.provider.StringProvider
import com.seigneur.gauvain.presentation.common.model.PhotoUiModel

class HomeUiMapper(private val stringProvider: StringProvider) {

    fun toPhotoUiModel(photo: Photo): PhotoUiModel =
        PhotoUiModel(
            photo.id,
            photo.description ?: stringProvider.noDescriptionProvided
        )

}