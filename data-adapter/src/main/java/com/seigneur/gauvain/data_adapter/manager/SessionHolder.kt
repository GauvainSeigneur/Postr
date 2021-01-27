package com.seigneur.gauvain.data_adapter.manager

import com.seigneur.gauvain.data_adapter.service.AUTH_INITIAL_URI
import com.seigneur.gauvain.data_adapter.service.AUTH_REDIRECT_URI

/**
 * Simple object which encapsulates data needed from several layers of the app.
 */
object SessionHolder {
    var accessToken: String? = null
    const val INITIAL_AUTH_URI = AUTH_INITIAL_URI
    const val REDIRECTION_URI =  AUTH_REDIRECT_URI
}