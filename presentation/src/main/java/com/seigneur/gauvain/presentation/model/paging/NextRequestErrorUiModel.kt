package com.seigneur.gauvain.presentation.model.paging

data class NextRequestErrorUiModel(
    val description: String,
    val buttonLabel: String,
    val clickAction : () -> Unit
)