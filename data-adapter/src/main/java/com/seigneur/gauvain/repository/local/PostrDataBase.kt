package com.seigneur.gauvain.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seigneur.gauvain.repository.models.local.TokenEntity
import com.seigneur.gauvain.repository.utils.RoomConverter

@Database(entities = [TokenEntity::class], version = 1)
@TypeConverters(RoomConverter::class)
abstract class PostrDataBase : RoomDatabase() {
     // DAO
    abstract fun tokenDao(): TokenDao
}


