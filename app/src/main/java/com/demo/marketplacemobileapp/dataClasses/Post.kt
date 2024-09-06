package com.demo.marketplacemobileapp.dataClasses

data class Post(
    val id: Long,
    val name: String,
    val views: Long,
    val description: String,
    val product: Product,
    val user: User,
    val likedUsers: List<User>,
    val tags: List<Tag>,
    val images: List<ImageClass>,
    val reviews: List<Long>
)
