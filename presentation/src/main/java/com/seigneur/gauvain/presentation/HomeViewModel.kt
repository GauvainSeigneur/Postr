package com.seigneur.gauvain.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.usecase.GetPhotoListUseCase
import com.seigneur.gauvain.presentation.model.AuthenticationState
import com.seigneur.gauvain.presentation.model.livedata.LiveDataState
import com.seigneur.gauvain.presentation.model.PhotoUiModel
import com.seigneur.gauvain.presentation.model.livedata.ErrorData
import com.seigneur.gauvain.presentation.model.livedata.ErrorDataType
import com.seigneur.gauvain.presentation.utils.ioJob

private typealias PhotoListState = LiveDataState<List<PhotoUiModel>>

class HomeViewModel(
    private val getPhotoListUseCase: GetPhotoListUseCase
) : ViewModel() {

    private val photoListState: MutableLiveData<PhotoListState> by lazy {
        ioJob {
            getPhotoList()
        }
        MutableLiveData<PhotoListState>()
    }
    val photoListData: LiveData<PhotoListState> by lazy {
        photoListState
    }


    private suspend fun getPhotoList() {
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
    }

}