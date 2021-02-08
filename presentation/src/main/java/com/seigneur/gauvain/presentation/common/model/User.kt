package com.seigneur.gauvain.presentation.common.model


sealed class SearchResultUiModel {
    data class User(
        val id: String,
        val name: String
    ) : SearchResultUiModel()

    data class Photo(
        val id: String
    ) : SearchResultUiModel()

    data class Collection(
        val id: String
    ) : SearchResultUiModel()
}
