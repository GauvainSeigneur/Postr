package com.seigneur.gauvain.presentation.pagedlist

import com.seigneur.gauvain.presentation.model.paging.NextRequestErrorUiModel
import com.seigneur.gauvain.presentation.provider.StringProvider

class NextRequestUiMapper(private val stringProvider: StringProvider) {

    fun toNextRequestErrorUiModel(action: () -> Unit): NextRequestErrorUiModel =
        NextRequestErrorUiModel(
            description = "issue",
            buttonLabel = "label",
            clickAction = { action() }
        )
}