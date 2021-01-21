package com.seigneur.gauvain.domain.utils

import com.seigneur.gauvain.domain.models.OutCome
import com.seigneur.gauvain.domain.models.providers.BaseProviderException
import com.seigneur.gauvain.domain.models.providers.request.RequestExceptionContent
import kotlin.reflect.KClass

/**
 * Call a provider which is a suspend function
 * handle expected exception
 * throw the other one we can catch because we miss something
 */
suspend fun <T> callProvider(
    providerCall: suspend () -> T,
    exception: KClass<out BaseProviderException>
): OutCome<T> {
    return try {
        val result = providerCall.invoke()
        OutCome.Success(result)
    } catch (e: BaseProviderException) {
        if (exception.isInstance(value = e)) {
            OutCome.Error(RequestExceptionContent( e.type, e.description))
        } else {
            //throw it as we miss something in Repository/UseCase
            throw e
        }
    }
}