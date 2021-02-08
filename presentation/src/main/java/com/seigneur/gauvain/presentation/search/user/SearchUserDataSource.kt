package com.seigneur.gauvain.presentation.search.user

import com.seigneur.gauvain.domain.models.User
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.usecase.SearchUserUseCase
import com.seigneur.gauvain.presentation.common.model.SearchResultUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.BaseListDataSource
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper
import com.seigneur.gauvain.presentation.search.SearchUiMapper

class SearchUserDataSource(
    viewModel: SearchUserViewModel,
    private val searchUserUseCase: SearchUserUseCase,
    nextRequestUiMapper: NextRequestUiMapper,
    private val uiMapper: SearchUiMapper,
    private val query: String
) : BaseListDataSource<SearchUserViewModel, List<User>, Long, SearchResultUiModel.User>(
    viewModel, nextRequestUiMapper
) {

    override val firstPageKey: Long = 1L

    override val firstNextPageKey: Long = 2L

    override fun nextKey(currentKey: Long): Long = currentKey + 1

    override suspend fun loadInitialRequest(keyPage: Long, pageSize: Int): OutCome<List<User>> =
        searchUserUseCase(query, keyPage, pageSize)

    override suspend fun loadAfterRequest(keyPage: Long, pageSize: Int): OutCome<List<User>> =
        searchUserUseCase(query, keyPage, pageSize)

    override fun mapResult(data: List<User>): List<SearchResultUiModel.User> {
        return data.map {
            uiMapper.toUserResultsUiModel(it)
        }
    }
}
