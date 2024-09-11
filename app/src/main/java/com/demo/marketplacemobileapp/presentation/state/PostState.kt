package com.demo.marketplacemobileapp.presentation.state

import com.demo.marketplacemobileapp.domain.model.Post

data class PostState (
    val isLoading: Boolean = false,
    val post: Post? = null,
    val error: String = ""
)