package com.seigneur.gauvain.domain.models.repository

data class RepositoryExceptionContent(
    val type: RepositoryExceptionType,
    val message: String? = null
)