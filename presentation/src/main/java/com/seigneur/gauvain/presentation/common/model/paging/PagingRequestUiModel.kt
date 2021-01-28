package com.seigneur.gauvain.presentation.common.model.paging

sealed class PagingRequestUiModel {
    data class Initial(val state: RequestStateUiModel) : PagingRequestUiModel()
    data class Next(val state: RequestStateUiModel) : PagingRequestUiModel()
}