package com.demo.marketplacemobileapp.presentation.viewModel.post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.marketplacemobileapp.common.Resource
import com.demo.marketplacemobileapp.domain.useCase.post.getPost.GetPostByIdUseCase
import com.demo.marketplacemobileapp.presentation.state.PostState
import com.demo.marketplacemobileapp.presentation.viewModel.login.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostByIdUseCase: GetPostByIdUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(PostState())
    val state: State<PostState> = _state

    fun getPostById(id: Long, token: String) {
        getPostByIdUseCase(id, token).onEach { result ->
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
        }.launchIn(viewModelScope)
    }

    fun addProductToCart() {

    }
}