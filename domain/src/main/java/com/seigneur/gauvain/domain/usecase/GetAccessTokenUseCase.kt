package com.seigneur.gauvain.domain.usecase

import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.models.OutCome
import com.seigneur.gauvain.domain.providers.GetAccessTokenException
import com.seigneur.gauvain.domain.providers.GetAccessTokenProvider
import com.seigneur.gauvain.domain.utils.callProvider

class GetAccessTokenUseCase(private val getAccessTokenProvider: GetAccessTokenProvider) {

    suspend operator fun invoke(code: String): OutCome<Token> {
        return callProvider(
            { getAccessTokenProvider.getAccessToken(code) },
            GetAccessTokenException::class
        )
    }
}