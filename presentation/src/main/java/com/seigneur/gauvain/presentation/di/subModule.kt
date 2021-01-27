package com.seigneur.gauvain.presentation.di

import com.seigneur.gauvain.domain.di.useCaseModule
import com.seigneur.gauvain.data_adapter.di.adapterModule
import com.seigneur.gauvain.data_adapter.di.databaseModule
import com.seigneur.gauvain.data_adapter.di.managerModule
import com.seigneur.gauvain.data_adapter.di.remoteDataSourceModule

val dataRemoteDataSourceModule = remoteDataSourceModule
val dataAdapterModule = adapterModule
val dataDataBaseModule = databaseModule
val dataManagerModule = managerModule
val domainUseCaseModule = useCaseModule