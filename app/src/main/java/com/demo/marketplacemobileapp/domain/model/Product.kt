package com.demo.marketplacemobileapp.domain.model

data class Product(
    val id: Long,
    val price: Float,
    val name: String,
    val post: Post
)