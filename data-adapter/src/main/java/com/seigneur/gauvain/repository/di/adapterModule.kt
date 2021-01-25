package com.seigneur.gauvain.repository.di

import com.seigneur.gauvain.domain.repository.GetAccessTokenRepository
import com.seigneur.gauvain.domain.repository.GetPhotoRepository
import com.seigneur.gauvain.domain.repository.InsertTokenRepository
import com.seigneur.gauvain.repository.repository.GetAccessTokenRepositoryImpl
import com.seigneur.gauvain.repository.repository.GetPhotoRepositoryImpl
import com.seigneur.gauvain.repository.repository.InsertTokenRepositoryImpl
import org.koin.dsl.module

val adapterModule = module {
    single<GetAccessTokenRepository> {
        GetAccessTokenRepositoryImpl(
            get(),
            get()
        )
    }
    single<InsertTokenRepository> {
        InsertTokenRepositoryImpl(
            get()
        )
    }
    single<GetPhotoRepository> {
        GetPhotoRepositoryImpl(
            get()
        )
    }
}