package com.seigneur.gauvain.data_adapter.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TokenEntity(
    @PrimaryKey val id: Int = 0,
    val date: String,
    val value: String
)
