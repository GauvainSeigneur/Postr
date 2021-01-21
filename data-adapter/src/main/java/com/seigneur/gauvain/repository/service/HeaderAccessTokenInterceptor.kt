package com.seigneur.gauvain.repository.service

import com.seigneur.gauvain.repository.repository.SessionRepository
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response
import kotlin.jvm.Throws

class HeaderAccessTokenInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Accept-Version", "v1")
        val tokenValue = SessionRepository.accessToken
        if (!tokenValue.isNullOrEmpty()) {
            builder.addHeader("Authorization", "Bearer $tokenValue")
        }
        return chain.proceed(builder.build())
    }
}