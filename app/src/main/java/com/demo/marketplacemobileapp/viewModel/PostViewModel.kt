package com.demo.marketplacemobileapp.viewModel

import androidx.lifecycle.ViewModel
import com.demo.marketplacemobileapp.config.config
import com.demo.marketplacemobileapp.domain.model.Post
import com.demo.marketplacemobileapp.data.remote.dto.converter.PostConverter
import com.demo.marketplacemobileapp.data.remote.dto.PostDTO
import com.demo.marketplacemobileapp.data.remote.requests.HTTPClient
import com.demo.marketplacemobileapp.data.remote.requests.getProductById
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Request

class PostViewModel : ViewModel() {

    suspend fun getPostById(id: Long): Post {
        val client = HTTPClient.client

        val request = Request.Builder()
            .url("${config.BASE_URL}/post/$id")
            .get()
            .build()

        return withContext(Dispatchers.IO) {
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                val responseData = response.body?.string()
                val postDTO = parsePost(responseData)
                val product = getProductById(postDTO.product)
                val post = PostConverter.toEntity(postDTO)
                post.product = product
                post
            } else {
                throw Exception("Network request failed with status code ${response.code}")
            }
        }
    }

    fun parsePost(responseData: String?): PostDTO {
        return Gson().fromJson(responseData, PostDTO::class.java)
    }
}
