package com.seigneur.gauvain.presentation.common.pagedlist

import com.seigneur.gauvain.presentation.common.model.paging.RequestErrorUiModel
import com.seigneur.gauvain.presentation.common.provider.StringProvider

class NextRequestUiMapper(private val stringProvider: StringProvider) {

    fun toRequestErrorUiModel(action: () -> Unit): RequestErrorUiModel =
        RequestErrorUiModel(
            description = "issue",
            buttonLabel = "label",
            clickAction = { action() }
        )
}