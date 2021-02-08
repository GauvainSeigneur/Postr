package com.seigneur.gauvain.presentation.di

import com.seigneur.gauvain.presentation.home.HomeViewModel
import com.seigneur.gauvain.presentation.login.LogInViewModel
import com.seigneur.gauvain.presentation.search.SearchViewModel
import com.seigneur.gauvain.presentation.search.collection.SearchCollectionViewModel
import com.seigneur.gauvain.presentation.search.photo.SearchPhotoViewModel
import com.seigneur.gauvain.presentation.search.user.SearchUserViewModel
import com.seigneur.gauvain.presentation.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LogInViewModel(get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { SearchViewModel() }
    viewModel { SearchUserViewModel(get(), get(), get()) }
    viewModel { SearchPhotoViewModel(get(), get(), get()) }
    viewModel { SearchCollectionViewModel(get(), get(), get()) }
}