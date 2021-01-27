package com.seigneur.gauvain.domain.usecase

import com.seigneur.gauvain.domain.models.Photo
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.repository.GetPhotoException
import com.seigneur.gauvain.domain.repository.GetPhotoRepository
import com.seigneur.gauvain.domain.utils.callRepository

class GetPhotoListUseCase(
    private val getPhotoRepository: GetPhotoRepository
) {

    suspend operator fun invoke(page: Long, perPage: Int, order_by: String?): OutCome<List<Photo>> {
        return callRepository(
            { getPhotoRepository.getPhotos(page, perPage, order_by) },
            GetPhotoException::class
        )
    }
}
