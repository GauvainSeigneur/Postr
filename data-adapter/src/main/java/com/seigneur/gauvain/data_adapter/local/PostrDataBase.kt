package com.seigneur.gauvain.data_adapter.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seigneur.gauvain.data_adapter.models.local.TokenEntity
import com.seigneur.gauvain.data_adapter.utils.RoomConverter

@Database(entities = [TokenEntity::class], version = 1)
@TypeConverters(RoomConverter::class)
abstract class PostrDataBase : RoomDatabase() {
     // DAO
    abstract fun tokenDao(): TokenDao
}


