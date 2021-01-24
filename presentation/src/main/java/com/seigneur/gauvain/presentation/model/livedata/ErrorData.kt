package com.seigneur.gauvain.presentation.model.livedata

import androidx.annotation.DrawableRes

data class ErrorData(
    val type: ErrorDataType,
    val title: String,
    val description: String? = null,
    val buttonText: String? = null,
    @DrawableRes
    val iconRes: Int? = null
)

enum class ErrorDataType {
    INFORMATIVE,
    RECOVERABLE,
    NOT_RECOVERABLE
}


