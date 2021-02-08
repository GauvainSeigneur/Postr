package com.seigneur.gauvain.presentation.home

import androidx.paging.DataSource
import com.seigneur.gauvain.presentation.common.model.PhotoUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper

class PhotoListDataSourceFactory(
    homeViewModel: HomeViewModel,
    uiMapper: HomeUiMapper,
    nextRequestUiMapper: NextRequestUiMapper
) : DataSource.Factory<Long, PhotoUiModel>() {

    private val dataSource = PhotoListDataSource(nextRequestUiMapper, homeViewModel, uiMapper)

    override fun create(): DataSource<Long, PhotoUiModel> {
        return dataSource
    }

    val requestStateData = dataSource.requestStateData
}
