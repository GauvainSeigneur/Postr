package com.seigneur.gauvain.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.presentation.model.PhotoUiModel
import com.seigneur.gauvain.presentation.model.paging.NextRequestStateUiModel
import com.seigneur.gauvain.presentation.pagedlist.NextRequestUiMapper
import com.seigneur.gauvain.presentation.utils.ioJob

class PhotoListDataSource(
    private val homeViewModel: HomeViewModel,
    private val uiMapper: HomeUiMapper,
    private val nextRequestUiMapper: NextRequestUiMapper
) :
    PageKeyedDataSource<Int, PhotoUiModel>() {

    val nextRequestStateData: MutableLiveData<NextRequestStateUiModel> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PhotoUiModel>
    ) {
        nextRequestStateData.postValue(NextRequestStateUiModel.Loading)
        homeViewModel.ioJob {
            when (val outcome = homeViewModel.getListOfPhotos(1)) {
                is OutCome.Success -> {
                    val list = outcome.data.map {
                        uiMapper.toPhotoUiModel(it)
                    }
                    callback.onResult(list, 0, list.count(), null, 2)
                    nextRequestStateData.postValue(NextRequestStateUiModel.Done)
                }
                is OutCome.Error -> {
                    nextRequestStateData.postValue(
                        NextRequestStateUiModel.Error(
                            nextRequestUiMapper.toNextRequestErrorUiModel {

                            }
                        )
                    )
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoUiModel>) {
        nextRequestStateData.postValue(NextRequestStateUiModel.Loading)

        homeViewModel.ioJob {
            when (val outcome = homeViewModel.getListOfPhotos(params.key.toLong())) {
                is OutCome.Success -> {
                    val list = outcome.data.map {
                        uiMapper.toPhotoUiModel(it)
                        PhotoUiModel(
                            it.id,
                            it.description ?: "no description provided"
                        )
                    }
                    callback.onResult(list, params.key + 1)
                    nextRequestStateData.postValue(NextRequestStateUiModel.Done)
                }
                is OutCome.Error -> {
                    nextRequestStateData.postValue(
                        NextRequestStateUiModel.Error(
                            nextRequestUiMapper.toNextRequestErrorUiModel {
                                loadAfter(params, callback)
                            }
                        )
                    )
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoUiModel>) {

    }

}
