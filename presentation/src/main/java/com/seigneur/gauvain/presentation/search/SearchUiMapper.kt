package com.seigneur.gauvain.presentation.search

import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.PhotoCollection
import com.seigneur.gauvain.domain.models.User
import com.seigneur.gauvain.presentation.common.model.SearchResultUiModel

class SearchUiMapper {

    fun toUserResultsUiModel(user: User): SearchResultUiModel.User =
        SearchResultUiModel.User(
            user.id,
            user.name
        )

    fun toPhotoResultsUiModel(photo: Photo): SearchResultUiModel.Photo =
        SearchResultUiModel.Photo(
            photo.id
        )

    fun toCollectionResultsUiModel(collection: PhotoCollection): SearchResultUiModel.Collection =
        SearchResultUiModel.Collection(
            collection.id
        )

}