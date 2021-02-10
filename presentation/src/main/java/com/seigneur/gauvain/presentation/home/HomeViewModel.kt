package com.seigneur.gauvain.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.usecase.GetPhotoListUseCase
import com.seigneur.gauvain.presentation.common.mapper.PhotoUiModelMapper
import com.seigneur.gauvain.presentation.common.model.PhotoUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper

class HomeViewModel(
    private val getPhotoListUseCase: GetPhotoListUseCase,
    uiModelMapper: PhotoUiModelMapper,
    nextRequestUiMapper: NextRequestUiMapper
) : ViewModel() {

    private val config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setInitialLoadSizeHint(INITIAL_PAGE_SIZE)
        .setEnablePlaceholders(true)
        .build()
    private val dataSourceFactory = PhotoListDataSourceFactory(this, uiModelMapper, nextRequestUiMapper)

    var list: LiveData<PagedList<PhotoUiModel>>? = null
    val pagingRequestStateData = dataSourceFactory.requestStateData

    init {
        config?.let {
            list = LivePagedListBuilder(dataSourceFactory, it).build()
        }
    }

    suspend fun getListOfPhotos(page: Long, pageSize: Int): OutCome<List<Photo>> {
        return getPhotoListUseCase(page, pageSize, null)
    }

    companion object {
        private const val PAGE_SIZE = 30
        private const val INITIAL_PAGE_SIZE = 45
    }

}