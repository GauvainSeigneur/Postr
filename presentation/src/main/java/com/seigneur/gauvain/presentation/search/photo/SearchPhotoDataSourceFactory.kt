package com.seigneur.gauvain.presentation.search.photo

import androidx.paging.DataSource
import com.seigneur.gauvain.domain.usecase.SearchPhotoUseCase
import com.seigneur.gauvain.presentation.common.model.SearchResultUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper
import com.seigneur.gauvain.presentation.search.SearchUiMapper

class SearchPhotoDataSourceFactory(
    searchViewModel: SearchPhotoViewModel,
    searchPhotoUseCase: SearchPhotoUseCase,
    searchUiMapper: SearchUiMapper,
    nextRequestUiMapper: NextRequestUiMapper,
    query: String,
) : DataSource.Factory<Long, SearchResultUiModel.Photo>() {

    private val dataSource = SearchPhotoDataSource(
        searchViewModel,
        searchPhotoUseCase,
        nextRequestUiMapper,
        searchUiMapper,
        query
    )

    override fun create(): DataSource<Long, SearchResultUiModel.Photo> {
        return dataSource
    }

    val requestStateData = dataSource.requestStateData
}
