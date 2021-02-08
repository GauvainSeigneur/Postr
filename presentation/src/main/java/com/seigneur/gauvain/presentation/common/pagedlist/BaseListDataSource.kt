package com.seigneur.gauvain.presentation.common.pagedlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PageKeyedDataSource
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.presentation.common.model.paging.PagingRequestUiModel
import com.seigneur.gauvain.presentation.common.model.paging.RequestStateUiModel
import com.seigneur.gauvain.presentation.utils.ioJob

/**
 * Base class for pagingDataSource
 * it allows to perform same behavior on each pagingDataSource and reuse same code
 */
abstract class BaseListDataSource<V : ViewModel, T, Key, Value>(
    private val viewModel: V,
    private val nextRequestUiMapper: NextRequestUiMapper
) : PageKeyedDataSource<Key, Value>() {

    /**
     * Get the request state
     */
    val requestStateData: MutableLiveData<PagingRequestUiModel> = MutableLiveData()

    /**
     * Define request for initial and after request
     */
    abstract suspend fun loadInitialRequest(keyPage: Key, pageSize: Int): OutCome<T>
    abstract suspend fun loadAfterRequest(keyPage: Key, pageSize: Int): OutCome<T>
    abstract fun mapResult(data: T): List<Value>

    /**
     * Define page for each request
     */
    abstract val firstPageKey: Key
    abstract val firstNextPageKey: Key
    abstract fun nextKey(currentKey: Key): Key

    override fun loadInitial(
        params: LoadInitialParams<Key>,
        callback: LoadInitialCallback<Key, Value>
    ) {
        handleFirstLoad(params, callback)
    }

    override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        handleLoadAfter(params, callback)
    }

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        // do nothing
    }

    private fun handleFirstLoad(
        params: LoadInitialParams<Key>,
        callback: LoadInitialCallback<Key, Value>
    ) {
        viewModel.ioJob {
            when (val outcome = loadInitialRequest(firstPageKey, params.requestedLoadSize)) {
                is OutCome.Success -> {
                    val list = mapResult(outcome.data)
                    callback.onResult(list, 0, list.count(), null, firstNextPageKey)
                    requestStateData.postValue(
                        PagingRequestUiModel.Initial(
                            RequestStateUiModel.Done
                        )
                    )
                }
                is OutCome.Error -> {
                    requestStateData.postValue(
                        PagingRequestUiModel.Initial(
                            RequestStateUiModel.Error(
                                nextRequestUiMapper.toRequestErrorUiModel {
                                    loadInitial(params, callback)
                                }
                            )
                        )
                    )
                }
            }
        }
    }


    private fun handleLoadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        requestStateData.postValue(PagingRequestUiModel.Next(RequestStateUiModel.Loading))
        viewModel.ioJob {
            when (val outcome =
                loadAfterRequest(params.key, params.requestedLoadSize)) {
                is OutCome.Success -> {
                    val list = mapResult(outcome.data)
                    requestStateData.postValue(PagingRequestUiModel.Next(RequestStateUiModel.Done))
                    callback.onResult(list, nextKey(params.key))
                }
                is OutCome.Error -> {
                    requestStateData.postValue(
                        PagingRequestUiModel.Next(
                            RequestStateUiModel.Error(
                                nextRequestUiMapper.toRequestErrorUiModel {
                                    loadAfter(params, callback)
                                }
                            )
                        )
                    )
                }
            }
        }
    }

}