package com.demo.marketplacemobileapp.domain.repository

import com.demo.marketplacemobileapp.data.remote.dto.PostDTO

interface PostRepository {

    suspend fun getPostList(): List<PostDTO>

    suspend fun getPostById(id: Long): PostDTO

}