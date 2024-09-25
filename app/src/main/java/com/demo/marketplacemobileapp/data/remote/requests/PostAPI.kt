package com.demo.marketplacemobileapp.data.remote.requests

import com.demo.marketplacemobileapp.data.remote.dto.PostDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PostAPI {

    @GET("/post")
    suspend fun getPostList(@Header("Authorization") token: String): List<PostDTO>

    @GET("/post/{id}")
    suspend fun getPostById(@Header("Authorization") token: String, @Path("id") id: Long): PostDTO
}