package com.demo.marketplacemobileapp.domain.repository

import com.demo.marketplacemobileapp.data.remote.dto.PostDTO

interface PostRepository {

    suspend fun getPosts(): List<PostDTO>

    suspend fun getPost(id: Long): PostDTO

}