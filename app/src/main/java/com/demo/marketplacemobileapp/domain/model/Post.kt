package com.demo.marketplacemobileapp.domain.model

data class Post(
    val id: Long,
    val name: String,
    val views: Long,
    val description: String,
    var product: Product? = null,
    val user: User? = null,
    val likedUsers: List<User>? = null,
    val tags: List<Tag>? = null,
    val images: List<Long>,
    val reviews: List<Long>? = null
)
