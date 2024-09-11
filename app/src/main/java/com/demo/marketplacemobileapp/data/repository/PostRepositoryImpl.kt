package com.demo.marketplacemobileapp.data.repository

import com.demo.marketplacemobileapp.data.remote.dto.PostDTO
import com.demo.marketplacemobileapp.data.remote.requests.PostAPI
import com.demo.marketplacemobileapp.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postAPI: PostAPI
): PostRepository {
    override suspend fun getPosts(): List<PostDTO> {
        return postAPI.getPosts()
    }

    override suspend fun getPost(id: Long): PostDTO {
        return postAPI.getPostById(id)
    }

}