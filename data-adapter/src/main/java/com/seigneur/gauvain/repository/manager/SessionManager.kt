package com.seigneur.gauvain.repository.manager

import com.seigneur.gauvain.domain.handlers.SessionHandler

class SessionManager : SessionHandler {

    override fun setSessionAccessToken(token: String) {
        SessionHolder.accessToken = token
    }
}