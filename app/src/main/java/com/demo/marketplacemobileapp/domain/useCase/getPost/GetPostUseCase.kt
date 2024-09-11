package com.demo.marketplacemobileapp.domain.useCase.getPost

import com.demo.marketplacemobileapp.common.Resource
import com.demo.marketplacemobileapp.data.remote.dto.toEntity
import com.demo.marketplacemobileapp.domain.model.Post
import com.demo.marketplacemobileapp.domain.repository.PostRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    operator fun invoke(id: Long): Flow<Resource<Post>> = flow {
        try {
            emit(Resource.Loading())
            val post = postRepository.getPost(id).toEntity()
            emit(Resource.Success(post))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage?: "Unexpected HTTP exception"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't connect to server"))
        }
    }
}