package com.demo.marketplacemobileapp.domain.model

data class Tag(
    val id: Long,
    val name: String,
    val Posts: List<Post>
)
