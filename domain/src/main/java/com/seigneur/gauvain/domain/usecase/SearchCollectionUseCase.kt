package com.seigneur.gauvain.domain.usecase

import com.seigneur.gauvain.domain.models.PhotoCollection
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.repository.SearchException
import com.seigneur.gauvain.domain.repository.SearchRepository
import com.seigneur.gauvain.domain.utils.callRepository

class SearchCollectionUseCase(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(
        query: String,
        page: Long,
        perPage: Int
    ): OutCome<List<PhotoCollection>> =
        callRepository(
            { searchRepository.searchCollection(query, page, perPage) },
            SearchException::class
        )
}
