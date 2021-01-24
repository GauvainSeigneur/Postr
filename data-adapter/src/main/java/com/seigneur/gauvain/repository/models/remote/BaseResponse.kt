package com.seigneur.gauvain.repository.models.remote

abstract class BaseResponse {
    //in case of error like wrong api key
    abstract val errors: List<String>?
}