package com.seigneur.gauvain.presentation.search.photo

import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.usecase.SearchPhotoUseCase
import com.seigneur.gauvain.presentation.common.model.SearchResultUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.BaseListDataSource
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper
import com.seigneur.gauvain.presentation.search.SearchUiMapper

class SearchPhotoDataSource(
    viewModel: SearchPhotoViewModel,
    private val searchPhotoUseCase: SearchPhotoUseCase,
    nextRequestUiMapper: NextRequestUiMapper,
    private val uiMapper: SearchUiMapper,
    private val query: String
) : BaseListDataSource<SearchPhotoViewModel, List<Photo>, Long, SearchResultUiModel.Photo>(
    viewModel, nextRequestUiMapper
) {

    override val firstPageKey: Long = 1L

    override val firstNextPageKey: Long = 2L

    override fun nextKey(currentKey: Long): Long = currentKey + 1

    override suspend fun loadInitialRequest(keyPage: Long, pageSize: Int): OutCome<List<Photo>> =
        searchPhotoUseCase(query, keyPage, pageSize)

    override suspend fun loadAfterRequest(keyPage: Long, pageSize: Int): OutCome<List<Photo>> =
        searchPhotoUseCase(query, keyPage, pageSize)

    override fun mapResult(data: List<Photo>): List<SearchResultUiModel.Photo> {
        return data.map {
            uiMapper.toPhotoResultsUiModel(it)
        }
    }
}
