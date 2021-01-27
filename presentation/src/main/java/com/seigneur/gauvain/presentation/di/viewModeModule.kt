package com.seigneur.gauvain.presentation.di

import com.seigneur.gauvain.presentation.home.HomeViewModel
import com.seigneur.gauvain.presentation.LogInViewModel
import com.seigneur.gauvain.presentation.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LogInViewModel(get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
}