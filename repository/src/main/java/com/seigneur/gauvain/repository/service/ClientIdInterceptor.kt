package com.seigneur.gauvain.repository.service

import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor which add client id as new query parameter in every request we make
 */
class ClientIdInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("client_id", CLIENT_ID_VALUE).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}