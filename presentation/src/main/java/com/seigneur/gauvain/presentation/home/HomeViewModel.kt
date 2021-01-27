package com.seigneur.gauvain.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.usecase.GetPhotoListUseCase
import com.seigneur.gauvain.presentation.common.model.PhotoUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper

class HomeViewModel(
    private val getPhotoListUseCase: GetPhotoListUseCase,
    uiMapper: HomeUiMapper,
    nextRequestUiMapper: NextRequestUiMapper
) : ViewModel() {

    private val config = PagedList.Config.Builder()
        .setPageSize(5)
        .setInitialLoadSizeHint(2 * 15)
        .setEnablePlaceholders(true)
        .build()
    private val dataSourceFactory = PhotoListDataSourceFactory(this, uiMapper, nextRequestUiMapper)

    var list: LiveData<PagedList<PhotoUiModel>>? = null
    val nextRequestStateData = dataSourceFactory.nextRequestStateData

    init {
        config?.let {
            list = LivePagedListBuilder(dataSourceFactory, it).build()
        }
    }

    suspend fun getListOfPhotos(page: Long): OutCome<List<Photo>> {
        return getPhotoListUseCase(page, 10, null)
    }

}