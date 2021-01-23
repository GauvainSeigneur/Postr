package com.seigneur.gauvain.repository.di

import com.seigneur.gauvain.domain.providers.GetAccessTokenProvider
import com.seigneur.gauvain.repository.repository.GetAccessTokenRepository
import org.koin.dsl.module

val adapterModule = module {
    single<GetAccessTokenProvider> {
        GetAccessTokenRepository(get())
    }
}