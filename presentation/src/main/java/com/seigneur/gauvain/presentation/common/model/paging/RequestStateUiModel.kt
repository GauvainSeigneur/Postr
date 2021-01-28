package com.seigneur.gauvain.presentation.common.model.paging

sealed class RequestStateUiModel {
    object Done : RequestStateUiModel()
    object Loading : RequestStateUiModel()
    data class Error(val data: RequestErrorUiModel) : RequestStateUiModel()
}