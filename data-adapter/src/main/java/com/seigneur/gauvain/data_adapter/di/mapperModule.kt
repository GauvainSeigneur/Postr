package com.seigneur.gauvain.data_adapter.di

import com.seigneur.gauvain.data_adapter.mapper.PhotoMapper
import org.koin.dsl.module

val mapperModule = module {
    factory {
        PhotoMapper()
    }
}