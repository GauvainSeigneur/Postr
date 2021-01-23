package com.seigneur.gauvain.presentation.di

import com.seigneur.gauvain.presentation.LogInViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LogInViewModel(get()) }
}