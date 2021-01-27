package com.seigneur.gauvain.presentation.common.model.paging

data class NextRequestErrorUiModel(
    val description: String,
    val buttonLabel: String,
    val clickAction : () -> Unit
)