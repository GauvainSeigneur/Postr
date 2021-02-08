package com.seigneur.gauvain.presentation.search.collection

import com.seigneur.gauvain.domain.models.PhotoCollection
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.usecase.SearchCollectionUseCase
import com.seigneur.gauvain.presentation.common.model.SearchResultUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.BaseListDataSource
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper
import com.seigneur.gauvain.presentation.search.SearchUiMapper

class SearchCollectionDataSource(
    viewModel: SearchCollectionViewModel,
    private val searchCollectionUseCase: SearchCollectionUseCase,
    nextRequestUiMapper: NextRequestUiMapper,
    private val uiMapper: SearchUiMapper,
    private val query: String
) : BaseListDataSource<SearchCollectionViewModel, List<PhotoCollection>, Long, SearchResultUiModel.Collection>(
    viewModel, nextRequestUiMapper
) {

    override val firstPageKey: Long = 1L

    override val firstNextPageKey: Long = 2L

    override fun nextKey(currentKey: Long): Long = currentKey + 1

    override suspend fun loadInitialRequest(
        keyPage: Long,
        pageSize: Int
    ): OutCome<List<PhotoCollection>> =
        searchCollectionUseCase(query, keyPage, pageSize)

    override suspend fun loadAfterRequest(
        keyPage: Long,
        pageSize: Int
    ): OutCome<List<PhotoCollection>> =
        searchCollectionUseCase(query, keyPage, pageSize)

    override fun mapResult(data: List<PhotoCollection>): List<SearchResultUiModel.Collection> {
        return data.map {
            uiMapper.toCollectionResultsUiModel(it)
        }
    }
}
