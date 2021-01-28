package com.seigneur.gauvain.presentation.common.pagedlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PageKeyedDataSource
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.presentation.common.model.paging.PagingRequestUiModel
import com.seigneur.gauvain.presentation.common.model.paging.RequestStateUiModel
import com.seigneur.gauvain.presentation.common.utils.ioJob

abstract class BasePagedListDataSource<V : ViewModel, T, U>(
    private val viewModel: V,
    private val nextRequestUiMapper: NextRequestUiMapper
) : PageKeyedDataSource<Int, U>() {

    private var pageSize = 5
    var nextPage: Long = 1L
        private set
    val requestStateData: MutableLiveData<PagingRequestUiModel> = MutableLiveData()

    abstract suspend fun initialCall(pageSize: Int): OutCome<T>
    abstract suspend fun nextCall(pageSize: Int, nextPage: Long): OutCome<T>
    abstract fun mapResult(data: T): List<U>
    abstract var nextPageKey: Long

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, U>
    ) {
        pageSize = params.requestedLoadSize
        requestStateData.postValue(
            PagingRequestUiModel.Initial(
                RequestStateUiModel.Loading
            )
        )
        viewModel.ioJob {
            when (val outcome = initialCall(pageSize)) {
                is OutCome.Success -> {
                    val list = mapResult(outcome.data)
                    callback.onResult(list, 0, list.count(), null, nextPageKey.toInt())
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

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, U>) {
        pageSize = params.requestedLoadSize
        nextPageKey = params.key.toLong()
        requestStateData.postValue(PagingRequestUiModel.Next(RequestStateUiModel.Loading))
        viewModel.ioJob {
            when (val outcome = nextCall(pageSize, nextPageKey)) {
                is OutCome.Success -> {
                    val list = mapResult(outcome.data)
                    callback.onResult(list, params.key + 1)
                    requestStateData.postValue(PagingRequestUiModel.Next(RequestStateUiModel.Done))
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

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, U>) {
        //do nothing
    }
}
