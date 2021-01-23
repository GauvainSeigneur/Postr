package com.seigneur.gauvain.domain.di

import com.seigneur.gauvain.domain.usecase.GetAccessTokenUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAccessTokenUseCase(get()) }
}