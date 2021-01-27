package com.seigneur.gauvain.data_adapter.repository

import android.util.Log
import com.seigneur.gauvain.domain.models.Token
import com.seigneur.gauvain.domain.models.repository.RepositoryResult
import com.seigneur.gauvain.domain.repository.InsertTokenException
import com.seigneur.gauvain.domain.repository.InsertTokenRepository
import com.seigneur.gauvain.data_adapter.local.TokenDao
import com.seigneur.gauvain.data_adapter.models.local.TokenEntity
import com.seigneur.gauvain.data_adapter.utils.performRoomCall

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
                Log.d("insertToken", "success ${result.data}")
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
