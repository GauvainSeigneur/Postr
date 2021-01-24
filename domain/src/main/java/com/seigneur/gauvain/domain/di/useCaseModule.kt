package com.seigneur.gauvain.domain.di

import com.seigneur.gauvain.domain.usecase.GetAccessTokenUseCase
import com.seigneur.gauvain.domain.usecase.GetCurrentTokenUseCase
import com.seigneur.gauvain.domain.usecase.SaveAccessTokenUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { SaveAccessTokenUseCase(get(), get()) }
    factory { GetAccessTokenUseCase(get(), get()) }
    factory { GetCurrentTokenUseCase(get(), get()) }
}