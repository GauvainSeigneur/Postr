package com.seigneur.gauvain.domain.usecase

import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.models.outcome.OutComeError
import com.seigneur.gauvain.domain.repository.GetAccessTokenException
import com.seigneur.gauvain.domain.repository.GetAccessTokenRepository
import com.seigneur.gauvain.domain.utils.callRepository

class GetAccessTokenUseCase(
    private val getAccessTokenRepository: GetAccessTokenRepository,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase
) {

    suspend operator fun invoke(code: String?): OutCome<Token> {
        code?.let {
            val result = callRepository(
                { getAccessTokenRepository.getAccessToken(code) },
                GetAccessTokenException::class
            )
            if (result is OutCome.Success) {
                // save it for the session
                saveAccessTokenUseCase(result.data)
            }
            return result
        } ?: return OutCome.Error(
            OutComeError.Domain(
                "code is null"
            )
        )
    }
}