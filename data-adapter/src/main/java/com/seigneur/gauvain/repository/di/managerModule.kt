package com.seigneur.gauvain.repository.di

import com.seigneur.gauvain.domain.handlers.SessionHandler
import com.seigneur.gauvain.repository.manager.SessionManager
import org.koin.dsl.module

val managerModule = module {
    single<SessionHandler> {
        SessionManager()
    }
}