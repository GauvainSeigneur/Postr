package com.seigneur.gauvain.presentation.di

import com.seigneur.gauvain.data_adapter.di.*
import com.seigneur.gauvain.domain.di.useCaseModule

val dataRemoteDataSourceModule = remoteDataSourceModule
val dataAdapterModule = adapterModule
val dataDataBaseModule = databaseModule
val dataManagerModule = managerModule
val domainUseCaseModule = useCaseModule
val dataMapperModule = mapperModule