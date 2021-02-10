package com.seigneur.gauvain.data_adapter.models.remote.search

data class SearchResponseModel<T>(
    val total:Int,
    val total_pages:Int,
    val results: List<T>
)