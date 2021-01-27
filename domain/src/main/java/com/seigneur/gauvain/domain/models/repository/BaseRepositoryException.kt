package com.seigneur.gauvain.domain.models.repository

abstract class BaseRepositoryException(
    val type: RepositoryExceptionType,
    val description: String?
) : Exception()