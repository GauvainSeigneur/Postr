package com.seigneur.gauvain.presentation.common.pagedlist

import com.seigneur.gauvain.presentation.common.model.paging.NextRequestErrorUiModel
import com.seigneur.gauvain.presentation.common.provider.StringProvider

class NextRequestUiMapper(private val stringProvider: StringProvider) {

    fun toNextRequestErrorUiModel(action: () -> Unit): NextRequestErrorUiModel =
        NextRequestErrorUiModel(
            description = "issue",
            buttonLabel = "label",
            clickAction = { action() }
        )
}