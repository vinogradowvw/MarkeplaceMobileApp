package com.demo.marketplacemobileapp.presentation.state

import com.demo.marketplacemobileapp.domain.model.Post

data class PostListState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String = ""
)
