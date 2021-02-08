package com.seigneur.gauvain.domain.usecase

import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.repository.SearchException
import com.seigneur.gauvain.domain.repository.SearchRepository
import com.seigneur.gauvain.domain.utils.callRepository

class SearchPhotoUseCase(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(query: String, page: Long, perPage: Int): OutCome<List<Photo>> =
        callRepository(
            { searchRepository.searchPhoto(query, page, perPage) },
            SearchException::class
        )
}
