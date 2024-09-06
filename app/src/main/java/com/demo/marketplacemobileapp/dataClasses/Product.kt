package com.demo.marketplacemobileapp.dataClasses

data class Product(
    val id: Long,
    val price: Float,
    val name: String,
    val post: Post
)