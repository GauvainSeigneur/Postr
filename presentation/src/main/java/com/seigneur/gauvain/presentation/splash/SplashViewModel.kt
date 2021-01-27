package com.seigneur.gauvain.presentation.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.usecase.GetCurrentTokenUseCase
import com.seigneur.gauvain.presentation.common.model.livedata.LiveDataState
import com.seigneur.gauvain.presentation.common.model.AuthenticationState
import com.seigneur.gauvain.presentation.common.utils.backgroundJob

private typealias SplashState = LiveDataState<AuthenticationState>

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
                splashState.postValue(LiveDataState.Success(AuthenticationState.AUTHENTICATED))
            }
            is OutCome.Error -> {
                splashState.postValue(LiveDataState.Success(AuthenticationState.NOT_AUTHENTICATED))
                Log.d("getCurrentToken", "fail ${outcome.outComeError}")
            }
        }
    }

}