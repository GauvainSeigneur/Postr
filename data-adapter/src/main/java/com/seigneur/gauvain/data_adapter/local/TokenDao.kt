package com.seigneur.gauvain.data_adapter.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seigneur.gauvain.data_adapter.models.local.TokenEntity

@Dao
interface TokenDao {

    /**
     * Insert TokenEntity in DB
     * return long (transaction id)
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTokenEntity(token: TokenEntity): Long

    /**
     * @return TokenEntity if exists, nothing if not
     */
    @Query("SELECT * FROM tokenentity")
    suspend fun getTokenEntity(): TokenEntity?

}
