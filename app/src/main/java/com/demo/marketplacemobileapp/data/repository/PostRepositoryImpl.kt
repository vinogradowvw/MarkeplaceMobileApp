package com.demo.marketplacemobileapp.data.repository

import com.demo.marketplacemobileapp.data.remote.dto.PostDTO
import com.demo.marketplacemobileapp.data.remote.requests.PostAPI
import com.demo.marketplacemobileapp.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postAPI: PostAPI
): PostRepository {
    override suspend fun getPostList(): List<PostDTO> {
        return postAPI.getPostList()
    }

    override suspend fun getPostById(id: Long): PostDTO {
        return postAPI.getPostById(id)
    }

}