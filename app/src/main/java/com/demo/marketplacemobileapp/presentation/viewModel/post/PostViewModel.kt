package com.demo.marketplacemobileapp.presentation.viewModel.post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.demo.marketplacemobileapp.common.Resource
import com.demo.marketplacemobileapp.domain.useCase.post.getPost.GetPostByIdUseCase
import com.demo.marketplacemobileapp.presentation.state.PostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostByIdUseCase: GetPostByIdUseCase,
    val id: Long
) {
    private val _state = mutableStateOf(PostState())
    val state: State<PostState> = _state

    init {
        getPostById(id)
    }

    private fun getPostById(id: Long) {
        getPostByIdUseCase(id).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = PostState(post = result.data)
                }
                is Resource.Loading -> {
                    _state.value = PostState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = PostState(error = result.message?: "Unexpected error")
                }
            }
        }
    }
}