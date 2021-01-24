package com.seigneur.gauvain.repository.repository

import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.models.repository.RepositoryResult
import com.seigneur.gauvain.domain.repository.InsertTokenException
import com.seigneur.gauvain.domain.repository.InsertTokenRepository
import com.seigneur.gauvain.repository.local.TokenDao
import com.seigneur.gauvain.repository.models.local.TokenEntity
import com.seigneur.gauvain.repository.utils.performRoomCall

class InsertTokenRepositoryImpl(private val tokenDao: TokenDao) :
    InsertTokenRepository {

    override suspend fun saveToken(token: Token): Long {
        val tokenEntity = TokenEntity(
            0,
            token.date,
            token.value
        )
        when (val result = performRoomCall {
            tokenDao.insertTokenEntity(tokenEntity)
        }) {
            is RepositoryResult.Success -> {
                return result.data
            }
            is RepositoryResult.Error -> {
                throw InsertTokenException(
                    result.errorContent.type,
                    result.errorContent.message
                )
            }
        }
    }
}
