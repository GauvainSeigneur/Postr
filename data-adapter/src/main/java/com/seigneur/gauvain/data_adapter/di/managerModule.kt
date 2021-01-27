package com.seigneur.gauvain.data_adapter.di

import com.seigneur.gauvain.domain.handlers.SessionHandler
import com.seigneur.gauvain.data_adapter.manager.SessionManager
import org.koin.dsl.module

val managerModule = module {
    single<SessionHandler> {
        SessionManager()
    }
}