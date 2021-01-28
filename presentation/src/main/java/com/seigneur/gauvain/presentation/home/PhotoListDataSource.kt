package com.seigneur.gauvain.presentation.home

import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.presentation.common.model.PhotoUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.BasePagedListDataSource
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper

class PhotoListDataSource(
    nextRequestUiMapper: NextRequestUiMapper,
    private val homeViewModel: HomeViewModel,
    private val uiMapper: HomeUiMapper,
) : BasePagedListDataSource<HomeViewModel, List<Photo>, PhotoUiModel>(
    homeViewModel, nextRequestUiMapper
) {

    override suspend fun initialCall(pageSize: Int): OutCome<List<Photo>> =
        homeViewModel.getListOfPhotos(1L, pageSize)

    override var nextPageKey: Long = 2

    override suspend fun nextCall(pageSize: Int, nextPage: Long): OutCome<List<Photo>> =
        homeViewModel.getListOfPhotos(nextPage, pageSize)

    override fun mapResult(data: List<Photo>): List<PhotoUiModel> {
        return data.map {
            uiMapper.toPhotoUiModel(it)
        }
    }
}
