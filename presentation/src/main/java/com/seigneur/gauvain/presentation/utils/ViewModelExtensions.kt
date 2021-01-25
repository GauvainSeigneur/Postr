package com.seigneur.gauvain.presentation.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.models.outcome.OutComeError
import com.seigneur.gauvain.presentation.model.livedata.ErrorData
import com.seigneur.gauvain.presentation.model.livedata.ErrorDataType
import kotlinx.coroutines.*

var ui: CoroutineDispatcher = Dispatchers.Main
var io: CoroutineDispatcher = Dispatchers.IO
var background: CoroutineDispatcher = Dispatchers.Default

fun ViewModel.uiJob(block: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch(ui) {
        block()
    }
}

fun ViewModel.ioJob(block: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch(io) {
        block()
    }
}

fun ViewModel.backgroundJob(block: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch(background) {
        block()
    }
}

// todo finish map this
fun ViewModel.outComeErrorToErrorData(error: OutCome.Error): ErrorData {
    return when(error.outComeError) {
        is OutComeError.Domain -> {
            ErrorData(ErrorDataType.INFORMATIVE, "unknown error")
        }
        is OutComeError.Repository -> {
            ErrorData(ErrorDataType.INFORMATIVE, "info")
        }
    }
}