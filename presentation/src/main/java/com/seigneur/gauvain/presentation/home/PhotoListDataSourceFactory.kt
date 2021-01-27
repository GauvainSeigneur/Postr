package com.seigneur.gauvain.presentation.home

import androidx.paging.DataSource
import com.seigneur.gauvain.presentation.model.PhotoUiModel
import com.seigneur.gauvain.presentation.pagedlist.NextRequestUiMapper

class PhotoListDataSourceFactory(
    homeViewModel: HomeViewModel,
    uiMapper: HomeUiMapper,
    nextRequestUiMapper: NextRequestUiMapper
) :
    DataSource.Factory<Int, PhotoUiModel>() {

    private val dataSource = PhotoListDataSource(homeViewModel, uiMapper, nextRequestUiMapper)

    override fun create(): DataSource<Int, PhotoUiModel> {
        return dataSource
    }

    val nextRequestStateData = dataSource.nextRequestStateData
}
