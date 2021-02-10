package com.seigneur.gauvain.presentation.home

import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.presentation.common.mapper.PhotoUiModelMapper
import com.seigneur.gauvain.presentation.common.model.PhotoUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.BaseListDataSource
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper

class PhotoListDataSource(
    nextRequestUiMapper: NextRequestUiMapper,
    private val homeViewModel: HomeViewModel,
    private val uiModelMapper: PhotoUiModelMapper,
) : BaseListDataSource<HomeViewModel, List<Photo>, Long, PhotoUiModel>(
    homeViewModel, nextRequestUiMapper
) {

    override val firstPageKey: Long = 1

    override val firstNextPageKey: Long = 2

    override fun nextKey(currentKey: Long): Long = currentKey + 1L

    override suspend fun loadInitialRequest(keyPage: Long, pageSize: Int): OutCome<List<Photo>> =
        homeViewModel.getListOfPhotos(keyPage, pageSize)

    override suspend fun loadAfterRequest(keyPage: Long, pageSize: Int): OutCome<List<Photo>> =
        homeViewModel.getListOfPhotos(keyPage, pageSize)

    override fun mapResult(data: List<Photo>): List<PhotoUiModel> {
        return data.map {
            uiModelMapper.toPhotoUiModel(it)
        }
    }
}