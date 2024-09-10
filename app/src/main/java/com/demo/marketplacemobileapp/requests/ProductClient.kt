package com.demo.marketplacemobileapp.requests

import com.demo.marketplacemobileapp.config.config
import com.demo.marketplacemobileapp.dataClasses.Product
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Request

suspend fun getProductById(id: Long): Product {
    val client = HTTPClient.client

    val request = Request.Builder()
        .url("${config.BASE_URL}/product/$id")
        .get()
        .build()

    return withContext(Dispatchers.IO) {
        val response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val responseData = response.body?.string()
            parseProduct(responseData)
        } else {
            throw Exception("Network request failed with status code ${response.code}")
        }
    }
}

fun parseProduct(responseData: String?): Product {
    return Gson().fromJson(responseData, Product::class.java)
}