package com.seigneur.gauvain.data_adapter.models.remote

abstract class BaseResponse {
    //in case of error like wrong api key
    abstract val errors: List<String>?
}