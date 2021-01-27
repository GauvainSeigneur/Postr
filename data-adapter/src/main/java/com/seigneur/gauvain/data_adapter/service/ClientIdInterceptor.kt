package com.seigneur.gauvain.data_adapter.service

import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response
import kotlin.jvm.Throws

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