package com.seigneur.gauvain.data_adapter.di

import androidx.room.Room
import com.seigneur.gauvain.data_adapter.local.PostrDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

    //Single module
    val databaseModule =module {
        single {
            Room.databaseBuilder(androidApplication(), PostrDataBase::class.java, "postr-db")
                .fallbackToDestructiveMigration()
                .build()
        }

        // TaskDAO instance (get instance from PostrDataBase)
        single { get<PostrDataBase>().tokenDao() }
    }






