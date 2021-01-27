package com.seigneur.gauvain.data_adapter.utils

import com.seigneur.gauvain.domain.models.repository.RepositoryExceptionContent
import com.seigneur.gauvain.domain.models.repository.RepositoryExceptionType
import com.seigneur.gauvain.domain.models.repository.RepositoryResult

suspend fun <T> performRoomCall(call: suspend () -> T): RepositoryResult<T> {
    return try {
        val result = call.invoke()
        RepositoryResult.Success(result)
    } catch (t: Throwable) {
        RepositoryResult.Error(
            RepositoryExceptionContent(
                RepositoryExceptionType.Local, t.message
            )
        )
    }
}

