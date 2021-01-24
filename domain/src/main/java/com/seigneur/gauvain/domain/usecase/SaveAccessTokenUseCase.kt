package com.seigneur.gauvain.domain.usecase

import com.seigneur.gauvain.domain.handlers.SessionHandler
import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.models.outcome.OutCome
import com.seigneur.gauvain.domain.models.outcome.OutComeError

class SaveAccessTokenUseCase(
    private val sessionHandler: SessionHandler
) {

    suspend operator fun invoke(token: Token): OutCome<Token> {
        // first set accessToken in sessionHolder
        sessionHandler.setSessionAccessToken(token.value)
        // todo call another useCase to save it in database too
        return OutCome.Error(OutComeError.Domain("lol"))
    }
}