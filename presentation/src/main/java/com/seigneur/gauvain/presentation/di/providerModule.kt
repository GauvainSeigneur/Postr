package com.seigneur.gauvain.presentation.di

import com.seigneur.gauvain.presentation.common.provider.StringController
import com.seigneur.gauvain.presentation.common.provider.StringProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val providerModule = module {
    factory<StringProvider> {
        StringController(context = androidContext())
    }
}