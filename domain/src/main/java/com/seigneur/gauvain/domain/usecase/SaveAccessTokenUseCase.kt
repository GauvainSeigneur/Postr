package com.seigneur.gauvain.domain.usecase

import com.seigneur.gauvain.domain.handlers.SessionHandler
import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.models.outcome.OutComeError
import com.seigneur.gauvain.domain.repository.GetAccessTokenException
import com.seigneur.gauvain.domain.repository.InsertTokenException
import com.seigneur.gauvain.domain.repository.InsertTokenRepository
import com.seigneur.gauvain.domain.utils.callRepository

class SaveAccessTokenUseCase(
    private val sessionHandler: SessionHandler,
    private val insertTokenRepository: InsertTokenRepository
) {

    suspend operator fun invoke(token: Token): OutCome<Long> {
        // set accessToken in sessionHolder
        sessionHandler.setSessionAccessToken(token.value)
        // now save it in dataBase
        return callRepository(
            { insertTokenRepository.saveToken(token) },
            InsertTokenException::class
        )

    }
}