package com.seigneur.gauvain.presentation.search.user

import androidx.paging.DataSource
import com.seigneur.gauvain.domain.usecase.SearchUserUseCase
import com.seigneur.gauvain.presentation.common.model.SearchResultUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper
import com.seigneur.gauvain.presentation.search.SearchUiMapper

class SearchUserDataSourceFactory(
    searchViewModel: SearchUserViewModel,
    searchUserUseCase: SearchUserUseCase,
    searchUiMapper: SearchUiMapper,
    nextRequestUiMapper: NextRequestUiMapper,
    query: String,
) : DataSource.Factory<Long, SearchResultUiModel.User>() {

    private val dataSource = SearchUserDataSource(
        searchViewModel,
        searchUserUseCase,
        nextRequestUiMapper,
        searchUiMapper,
        query
    )

    override fun create(): DataSource<Long, SearchResultUiModel.User> {
        return dataSource
    }

    val requestStateData = dataSource.requestStateData
}
