package com.seigneur.gauvain.repository.di

import com.seigneur.gauvain.domain.repository.GetAccessTokenRepository
import com.seigneur.gauvain.repository.repository.GetAccessTokenRepositoryImpl
import org.koin.dsl.module

val adapterModule = module {
    single<GetAccessTokenRepository> {
        GetAccessTokenRepositoryImpl(
            get()
        )
    }
}