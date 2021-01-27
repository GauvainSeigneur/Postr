package com.seigneur.gauvain.presentation.common.model.paging

sealed class NextRequestStateUiModel {
    object Done : NextRequestStateUiModel()
    object Loading : NextRequestStateUiModel()
    data class Error(val data: NextRequestErrorUiModel) : NextRequestStateUiModel()
}