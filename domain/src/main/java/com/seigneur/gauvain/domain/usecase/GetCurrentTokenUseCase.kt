package com.seigneur.gauvain.domain.usecase

import com.seigneur.gauvain.domain.handlers.SessionHandler
import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.repository.GetAccessTokenException
import com.seigneur.gauvain.domain.repository.GetAccessTokenRepository
import com.seigneur.gauvain.domain.utils.callRepository

class GetCurrentTokenUseCase(
    private val getAccessTokenRepository: GetAccessTokenRepository,
    private val sessionHandler: SessionHandler,
) {

    suspend operator fun invoke(): OutCome<Token> {
        val result = callRepository(
            { getAccessTokenRepository.getLocalToken() },
            GetAccessTokenException::class
        )
        if (result is OutCome.Success) {
            // set value for the session
            sessionHandler.setSessionAccessToken(result.data.value)
        }
        return result
    }
}
