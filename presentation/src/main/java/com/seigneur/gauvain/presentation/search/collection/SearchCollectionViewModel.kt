package com.seigneur.gauvain.presentation.search.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.seigneur.gauvain.domain.usecase.SearchCollectionUseCase
import com.seigneur.gauvain.presentation.common.model.SearchResultUiModel
import com.seigneur.gauvain.presentation.common.model.paging.PagingRequestUiModel
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper
import com.seigneur.gauvain.presentation.search.SearchUiMapper

class SearchCollectionViewModel(
    private val searchCollectionUseCase: SearchCollectionUseCase,
    private val uiMapper: SearchUiMapper,
    private val nextRequestUiMapper: NextRequestUiMapper
) : ViewModel() {

    // LiveData
    var list: LiveData<PagedList<SearchResultUiModel.Collection>>? = null
    var pagingRequestStateData: MutableLiveData<PagingRequestUiModel>? = null

    // Paging data
    private var config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setInitialLoadSizeHint(INITIAL_PAGE_SIZE)
        .setEnablePlaceholders(true)
        .build()
    private var dataSourceFactory: SearchCollectionDataSourceFactory? = null

    fun searchCollection(query: String) {
        dataSourceFactory =
            SearchCollectionDataSourceFactory(
                this,
                searchCollectionUseCase,
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