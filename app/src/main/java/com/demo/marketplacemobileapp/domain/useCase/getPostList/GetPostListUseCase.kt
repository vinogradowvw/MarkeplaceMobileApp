package com.demo.marketplacemobileapp.domain.useCase.getPostList

import com.demo.marketplacemobileapp.common.Resource
import com.demo.marketplacemobileapp.data.remote.dto.toEntity
import com.demo.marketplacemobileapp.domain.model.Post
import com.demo.marketplacemobileapp.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException

class GetPostListUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    operator fun invoke(): Flow<Resource<List<Post>>> = flow {
        try {
            emit(Resource.Loading())
            val posts: List<Post> = postRepository.getPosts().map { it.toEntity() }
            emit(Resource.Success(posts))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected HTTP exception"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't connect to server"))
        }
    }
}