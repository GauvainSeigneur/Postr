package com.seigneur.gauvain.data_adapter.di

import com.seigneur.gauvain.domain.repository.GetAccessTokenRepository
import com.seigneur.gauvain.domain.repository.GetPhotoRepository
import com.seigneur.gauvain.domain.repository.InsertTokenRepository
import com.seigneur.gauvain.data_adapter.repository.GetAccessTokenRepositoryImpl
import com.seigneur.gauvain.data_adapter.repository.GetPhotoRepositoryImpl
import com.seigneur.gauvain.data_adapter.repository.InsertTokenRepositoryImpl
import com.seigneur.gauvain.data_adapter.repository.SearchRepositoryImpl
import com.seigneur.gauvain.domain.repository.SearchRepository
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
    single<SearchRepository> {
        SearchRepositoryImpl(get())
    }
}