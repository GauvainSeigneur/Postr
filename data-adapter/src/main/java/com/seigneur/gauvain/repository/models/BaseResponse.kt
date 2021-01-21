package com.seigneur.gauvain.repository.models

abstract class BaseResponse {
    //in case of error like wrong api key
    abstract val errors: List<String>?
}