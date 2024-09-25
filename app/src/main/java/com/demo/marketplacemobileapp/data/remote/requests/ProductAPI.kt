package com.demo.marketplacemobileapp.data.remote.requests

import com.demo.marketplacemobileapp.data.remote.dto.ProductDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ProductAPI {

    @GET("/product/{id}")
    suspend fun getProductById(@Header("Authorization") token: String, @Path("id") id: Long): ProductDTO

}