package com.example.network

import okhttp3.Interceptor
import okhttp3.Response

class DecryptionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        // Decrypt response body
        return response
    }
}
