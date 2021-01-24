package com.seigneur.gauvain.postr

import android.app.Application
import com.seigneur.gauvain.presentation.di.*
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
                    dataDataBaseModule,
                    dataManagerModule,
                    domainUseCaseModule,
                    viewModelModule
                )
            )
        }
    }
}