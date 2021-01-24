package com.seigneur.gauvain.presentation.di

import com.seigneur.gauvain.domain.di.useCaseModule
import com.seigneur.gauvain.repository.di.adapterModule
import com.seigneur.gauvain.repository.di.databaseModule
import com.seigneur.gauvain.repository.di.remoteDataSourceModule

val dataRemoteDataSourceModule = remoteDataSourceModule
val dataAdapterModule = adapterModule
val dataDataBaseModule = databaseModule
val domainUseCaseModule = useCaseModule