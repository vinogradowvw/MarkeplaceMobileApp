package com.demo.marketplacemobileapp.domain.useCase.post.getPost

import android.util.Log
import com.demo.marketplacemobileapp.common.Resource
import com.demo.marketplacemobileapp.data.remote.dto.toEntity
import com.demo.marketplacemobileapp.domain.model.Post
import com.demo.marketplacemobileapp.domain.repository.PostRepository
import com.demo.marketplacemobileapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val productRepository: ProductRepository
) {
    operator fun invoke(id: Long, token: String): Flow<Resource<Post>> = flow {
        try {
            emit(Resource.Loading())
            val postDto = postRepository.getPostById(id, token)
            var post = postDto.toEntity()
            post.product = productRepository.getProductById(postDto.product, token).toEntity()
            emit(Resource.Success(post))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage?: "Unexpected HTTP exception"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't connect to server"))
        }
    }
}