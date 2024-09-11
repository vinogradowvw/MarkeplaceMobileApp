package com.demo.marketplacemobileapp.data.remote.requests

import com.demo.marketplacemobileapp.data.remote.dto.PostDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PostAPI {

    @GET("/post")
    suspend fun getPosts(): List<PostDTO>

    @GET("/post/{id}")
    suspend fun getPostById(@Path("id") id: Long): PostDTO
}