package com.seigneur.gauvain.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.presentation.model.PhotoUiModel
import com.seigneur.gauvain.presentation.utils.ioJob

sealed class ApiState {

    object Loading : ApiState()

    class Error(val error: Throwable) : ApiState()

    object Success : ApiState()
}

private const val TAG = "MoviesDataSource"

class MoviesDataSource(private val homeViewModel: HomeViewModel) :
    PageKeyedDataSource<Int, PhotoUiModel>() {

    val networkStatusLiveData: MutableLiveData<ApiState> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PhotoUiModel>) {
        networkStatusLiveData.postValue(ApiState.Loading)
        homeViewModel.ioJob {
            when (val outcome = homeViewModel.getListOfPhotos(1)) {
                is OutCome.Success -> {
                    val list = outcome.data.map {
                        PhotoUiModel(
                            it.id,
                            it.description ?: "no description provided"
                        )
                    }
                    callback.onResult(list, 0, list.count(), null, 2)
                    networkStatusLiveData.postValue(ApiState.Success)
                }
                is OutCome.Error -> {
                    networkStatusLiveData.postValue(
                        ApiState.Error(
                            Throwable("issue to map into UI")
                        )
                    )
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoUiModel>) {
        networkStatusLiveData.postValue(ApiState.Loading)

        homeViewModel.ioJob {
            when (val outcome = homeViewModel.getListOfPhotos(params.key.toLong())) {
                is OutCome.Success -> {
                    val list = outcome.data.map {
                        PhotoUiModel(
                            it.id,
                            it.description ?: "no description provided"
                        )
                    }
                    callback.onResult(list,  params.key + 1)
                    networkStatusLiveData.postValue(ApiState.Success)
                }
                is OutCome.Error -> {
                    networkStatusLiveData.postValue(
                        ApiState.Error(
                            Throwable("issue to map into UI")
                        )
                    )
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoUiModel>) {

    }

}

class MovieDatasourceFactory(private val homeViewModel: HomeViewModel) :
    DataSource.Factory<Int, PhotoUiModel>() {

    private val movieDataSource =
        MoviesDataSource(homeViewModel)

    override fun create(): DataSource<Int, PhotoUiModel> {
        return movieDataSource
    }

    fun getNetworkStatusLiveData(): MutableLiveData<ApiState> = movieDataSource.networkStatusLiveData
}
