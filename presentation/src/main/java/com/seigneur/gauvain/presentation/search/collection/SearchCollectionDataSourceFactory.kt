package com.seigneur.gauvain.presentation.search.collection

import androidx.paging.DataSource
import com.seigneur.gauvain.domain.usecase.SearchCollectionUseCase
import com.seigneur.gauvain.domain.usecase.SearchPhotoUseCase
import com.seigneur.gauvain.presentation.common.model.SearchResultUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper
import com.seigneur.gauvain.presentation.search.SearchUiMapper

class SearchCollectionDataSourceFactory(
    searchViewModel: SearchCollectionViewModel,
    searchCollectionUseCase: SearchCollectionUseCase,
    searchUiMapper: SearchUiMapper,
    nextRequestUiMapper: NextRequestUiMapper,
    query: String,
) : DataSource.Factory<Long, SearchResultUiModel.Collection>() {

    private val dataSource = SearchCollectionDataSource(
        searchViewModel,
        searchCollectionUseCase,
        nextRequestUiMapper,
        searchUiMapper,
        query
    )

    override fun create(): DataSource<Long, SearchResultUiModel.Collection> {
        return dataSource
    }

    val requestStateData = dataSource.requestStateData
}
