package com.demo.marketplacemobileapp.domain.repository

import com.demo.marketplacemobileapp.data.remote.dto.PostDTO

interface PostRepository {

    suspend fun getPostList(token: String): List<PostDTO>

    suspend fun getPostById(id: Long, token: String): PostDTO

}