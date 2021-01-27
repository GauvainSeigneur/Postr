package com.seigneur.gauvain.data_adapter.manager

import com.seigneur.gauvain.domain.handlers.SessionHandler

class SessionManager : SessionHandler {

    override fun setSessionAccessToken(token: String) {
        SessionHolder.accessToken = token
    }
}