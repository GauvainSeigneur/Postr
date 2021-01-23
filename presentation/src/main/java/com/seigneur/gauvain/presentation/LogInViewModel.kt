package com.seigneur.gauvain.presentation

import androidx.lifecycle.ViewModel
import com.seigneur.gauvain.domain.usecase.GetAccessTokenUseCase


class LogInViewModel(
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : ViewModel() {


}