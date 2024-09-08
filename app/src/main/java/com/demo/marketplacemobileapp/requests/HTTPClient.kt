package com.demo.marketplacemobileapp.requests

import okhttp3.OkHttpClient

object HTTPClient {
    val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .build()
}