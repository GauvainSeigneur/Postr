package com.seigneur.gauvain.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.usecase.GetCurrentTokenUseCase
import com.seigneur.gauvain.presentation.model.livedata.LiveDataState
import com.seigneur.gauvain.presentation.model.SplashData
import com.seigneur.gauvain.presentation.utils.backgroundJob

typealias SplashState = LiveDataState<SplashData>

class SplashViewModel(
    private val getCurrentTokenUseCase: GetCurrentTokenUseCase
) : ViewModel() {

    private val splashState: MutableLiveData<SplashState> by lazy {
        backgroundJob {
            getToken()
        }
        MutableLiveData<SplashState>()
    }
    val splashData: LiveData<SplashState> by lazy {
        splashState
    }

    private suspend fun getToken() {
        when (val outcome = getCurrentTokenUseCase()) {
            is OutCome.Success -> {
                splashState.postValue(LiveDataState.Success(SplashData.AUTHENTICATED))
            }
            is OutCome.Error -> {
                splashState.postValue(LiveDataState.Success(SplashData.NOT_AUTHENTICATED))
                Log.d("getCurrentToken", "fail ${outcome.outComeError}")
            }
        }
    }

}