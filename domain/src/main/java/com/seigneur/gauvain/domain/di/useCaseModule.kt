package com.seigneur.gauvain.domain.di

import com.seigneur.gauvain.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { SaveAccessTokenUseCase(get(), get()) }
    factory { GetAccessTokenUseCase(get(), get()) }
    factory { GetCurrentTokenUseCase(get(), get()) }
    factory { GetPhotoListUseCase(get()) }
    factory { SearchUserUseCase(get()) }
    factory { SearchPhotoUseCase(get()) }
    factory { SearchCollectionUseCase(get()) }
}