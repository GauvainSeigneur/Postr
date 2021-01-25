package com.seigneur.gauvain.presentation.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.usecase.GetPhotoListUseCase
import com.seigneur.gauvain.presentation.model.livedata.LiveDataState
import com.seigneur.gauvain.presentation.model.PhotoUiModel

private typealias PhotoListState = LiveDataState<List<PhotoUiModel>>

class HomeViewModel(
    private val getPhotoListUseCase: GetPhotoListUseCase
) : ViewModel() {

    var list: LiveData<PagedList<PhotoUiModel>>? = null

    private val config = PagedList.Config.Builder()
        .setPageSize(5)
        .setInitialLoadSizeHint(2 * 15)
        .setEnablePlaceholders(true)
        .build()

    private val datasourceFactory = MovieDatasourceFactory(this)

    init {

        config?.let {
            list = LivePagedListBuilder(datasourceFactory, it).build()
        }


    }

    suspend fun getListOfPhotos(page: Long): OutCome<List<Photo>> {
        return getPhotoListUseCase(page, 10, null)
    }


    /*private val photoListState: MutableLiveData<PhotoListState> by lazy {
        ioJob {
            getPhotoList()
        }
        MutableLiveData<PhotoListState>()
    }
    val photoListData: LiveData<PhotoListState> by lazy {
        photoListState
    }*/

    /*private suspend fun getPhotoList() {
        when (val outcome = getPhotoListUseCase(0, 30, null)) {
            is OutCome.Success -> {
                val list = outcome.data.map {
                    PhotoUiModel(
                        it.id,
                        it.description ?: "no description provided"
                    )
                }
                photoListState.postValue(LiveDataState.Success(list))
            }
            is OutCome.Error -> {
                photoListState.postValue(
                    LiveDataState.Error(
                        ErrorData(
                            ErrorDataType.INFORMATIVE,
                            "error"
                        )
                    )
                )
                Log.d("getPhotoList", "fail ${outcome.outComeError}")
            }
        }
    }*/

}