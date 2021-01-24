package com.seigneur.gauvain.presentation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.usecase.GetAccessTokenUseCase
import com.seigneur.gauvain.presentation.utils.ioJob
import com.seigneur.gauvain.repository.manager.SessionHolder


class LogInViewModel(
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : ViewModel() {

    val authUri = SessionHolder.INITIAL_AUTH_URI

    fun checkAuthUrl(url: Uri) {
        if (url.toString().startsWith(SessionHolder.REDIRECTION_URI)) {
            ioJob {
                getAccessToken(url.getQueryParameter(CODE_KEY))
            }
        } else {
            // do nothing
            Log.d("getAccessToken", "not the redirect URI $url")
        }

    }

    private suspend fun getAccessToken(code: String?) {
        when (val outcome = getAccessTokenUseCase(code)) {
            is OutCome.Success -> {
                Log.d("getAccessToken", "result ${outcome.data}")
            }
            is OutCome.Error -> {
                Log.d("getAccessToken", "fail ${outcome.outComeError}")
            }
        }
    }

    companion object {
        private const val CODE_KEY = "code"
    }
}