package com.seigneur.gauvain.presentation.common.model.paging

data class RequestErrorUiModel(
    val description: String,
    val buttonLabel: String,
    val clickAction : () -> Unit
)