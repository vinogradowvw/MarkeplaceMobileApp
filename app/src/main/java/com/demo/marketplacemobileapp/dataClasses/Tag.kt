package com.demo.marketplacemobileapp.dataClasses

data class Tag(
    val id: Long,
    val name: String,
    val Posts: List<Post>
)
