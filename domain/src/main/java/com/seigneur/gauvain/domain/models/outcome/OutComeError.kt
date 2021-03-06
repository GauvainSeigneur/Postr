package com.seigneur.gauvain.domain.models.outcome

import com.seigneur.gauvain.domain.models.repository.RepositoryExceptionContent

sealed class OutComeError {
    data class Repository(val exceptionContent: RepositoryExceptionContent) : OutComeError()
    data class Domain(val description: String) : OutComeError()
}