package com.seigneur.gauvain.domain.models.outcome

sealed class OutCome<out T> {
    data class Success<out T>(val data: T) : OutCome<T>()
    data class Error(val outComeError: OutComeError) : OutCome<Nothing>()
}