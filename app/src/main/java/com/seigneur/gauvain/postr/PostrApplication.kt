package com.seigneur.gauvain.postr

import android.app.Application
import com.seigneur.gauvain.presentation.di.dataAdapterModule
import com.seigneur.gauvain.presentation.di.dataRemoteDataSourceModule
import com.seigneur.gauvain.presentation.di.domainUseCaseModule
import com.seigneur.gauvain.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PostrApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PostrApplication)
            androidFileProperties()
            modules(
                listOf(
                    dataRemoteDataSourceModule,
                    dataAdapterModule,
                    domainUseCaseModule,
                    viewModelModule
                )
            )
        }
    }
}