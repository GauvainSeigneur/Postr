package com.seigneur.gauvain.presentation.di

import com.seigneur.gauvain.presentation.provider.StringController
import com.seigneur.gauvain.presentation.provider.StringProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val providerModule = module {
    factory<StringProvider> {
        StringController(context = androidContext())
    }
}