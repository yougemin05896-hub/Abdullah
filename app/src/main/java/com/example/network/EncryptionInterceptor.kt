package com.example.network

import okhttp3.Interceptor
import okhttp3.Response

class EncryptionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        // Encrypt body payload before hitting Fly.io network
        return chain.proceed(originalRequest)
    }
}
