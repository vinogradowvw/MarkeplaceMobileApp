package com.demo.marketplacemobileapp.data.remote.dto

import com.demo.marketplacemobileapp.domain.model.Post

data class PostDTO(
    val id: Long,
    val name: String,
    val views: Long,
    val description: String,
    val product: Long,
    val user: Long,
    val likedUsers: List<Long>,
    val tags: List<Long>,
    val images: List<Long>,
    val reviews: List<Long>
)

fun PostDTO.toEntity(): Post {
    return Post(
        id = id,
        description = description,
        name = name,
        views = views,
        images = images,
    )
}