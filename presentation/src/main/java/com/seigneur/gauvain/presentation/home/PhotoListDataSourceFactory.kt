package com.seigneur.gauvain.presentation.home

import androidx.paging.DataSource
import com.seigneur.gauvain.presentation.common.mapper.PhotoUiModelMapper
import com.seigneur.gauvain.presentation.common.model.PhotoUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper

class PhotoListDataSourceFactory(
    homeViewModel: HomeViewModel,
    uiModelMapper: PhotoUiModelMapper,
    nextRequestUiMapper: NextRequestUiMapper
) : DataSource.Factory<Long, PhotoUiModel>() {

    private val dataSource = PhotoListDataSource(nextRequestUiMapper, homeViewModel, uiModelMapper)

    override fun create(): DataSource<Long, PhotoUiModel> {
        return dataSource
    }

    val requestStateData = dataSource.requestStateData
}
