package com.seigneur.gauvain.presentation.search.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.seigneur.gauvain.domain.models.User
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.usecase.SearchPhotoUseCase
import com.seigneur.gauvain.domain.usecase.SearchUserUseCase
import com.seigneur.gauvain.presentation.common.model.SearchResultUiModel
import com.seigneur.gauvain.presentation.common.model.paging.PagingRequestUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper
import com.seigneur.gauvain.presentation.search.SearchUiMapper

class SearchPhotoViewModel(
    private val searchPhotoUseCase: SearchPhotoUseCase,
    private val uiMapper: SearchUiMapper,
    private val nextRequestUiMapper: NextRequestUiMapper
) : ViewModel() {

    // LiveData
    var list: LiveData<PagedList<SearchResultUiModel.Photo>>? = null
    var pagingRequestStateData: MutableLiveData<PagingRequestUiModel>? = null

    // Paging data
    private var config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setInitialLoadSizeHint(INITIAL_PAGE_SIZE)
        .setEnablePlaceholders(true)
        .build()
    private var dataSourceFactory: SearchPhotoDataSourceFactory? = null

    fun searchPhoto(query: String) {
        dataSourceFactory =
            SearchPhotoDataSourceFactory(
                this,
                searchPhotoUseCase,
                uiMapper,
                nextRequestUiMapper,
                query
            )
        list = LivePagedListBuilder(dataSourceFactory!!, config).build()
        pagingRequestStateData = dataSourceFactory?.requestStateData
    }

    companion object {
        const val PAGE_SIZE = 15
        const val INITIAL_PAGE_SIZE = 30
    }

}