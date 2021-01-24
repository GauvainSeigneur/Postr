package com.seigneur.gauvain.domain.utils

import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.models.outcome.OutComeError
import com.seigneur.gauvain.domain.models.repository.BaseRepositoryException
import com.seigneur.gauvain.domain.models.repository.RepositoryExceptionContent
import kotlin.reflect.KClass

/**
 * Call a provider which is a suspend function
 * handle expected exception
 * throw the other one we can catch because we miss something
 */
suspend fun <T> callRepository(
    providerCall: suspend () -> T,
    exception: KClass<out BaseRepositoryException>
): OutCome<T> {
    return try {
        val result = providerCall.invoke()
        OutCome.Success(result)
    } catch (e: BaseRepositoryException) {
        if (exception.isInstance(value = e)) {
            OutCome.Error(
                OutComeError.RemoteRequest(
                    RepositoryExceptionContent(
                        e.type,
                        e.description
                    )
                )
            )
        } else {
            //throw it as we miss something in Repository/UseCase
            throw e
        }
    }
}