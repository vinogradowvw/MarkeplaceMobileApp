package com.demo.marketplacemobileapp.dataClasses

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