package com.seigneur.gauvain.presentation.login

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.usecase.GetAccessTokenUseCase
import com.seigneur.gauvain.presentation.common.model.LoginUiModel
import com.seigneur.gauvain.presentation.common.model.livedata.ErrorData
import com.seigneur.gauvain.presentation.common.model.livedata.ErrorDataType
import com.seigneur.gauvain.presentation.common.model.livedata.LiveDataState
import com.seigneur.gauvain.presentation.common.utils.ioJob
import com.seigneur.gauvain.data_adapter.manager.SessionHolder

private typealias LoginState = LiveDataState<LoginUiModel>

class LogInViewModel(
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : ViewModel() {

    val authUri = SessionHolder.INITIAL_AUTH_URI

    private val loginState: MutableLiveData<LoginState> = MutableLiveData()
    val loginData: LiveData<LoginState> by lazy {
        loginState
    }

    fun checkAuthUrl(url: Uri) {
        if (url.toString().startsWith(SessionHolder.REDIRECTION_URI)) {
            ioJob {
                getAccessToken(url.getQueryParameter(CODE_KEY))
            }
        } else {
            // do nothing
        }
    }

    private suspend fun getAccessToken(code: String?) {
        when (val outcome = getAccessTokenUseCase(code)) {
            is OutCome.Success -> {
                loginState.postValue(
                    LiveDataState.Success(
                        LoginUiModel("bienvenue")
                    )
                )
            }
            is OutCome.Error -> {
                loginState.postValue(
                    LiveDataState.Error(
                        ErrorData(ErrorDataType.INFORMATIVE, "erreur")
                    )
                )
            }
        }
    }

    companion object {
        private const val CODE_KEY = "code"
    }
}