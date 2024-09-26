package com.demo.marketplacemobileapp.presentation.viewModel.post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.marketplacemobileapp.common.Resource
import com.demo.marketplacemobileapp.domain.useCase.post.getPostList.GetPostListUseCase
import com.demo.marketplacemobileapp.presentation.state.PostListState
import com.demo.marketplacemobileapp.presentation.viewModel.login.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostListUseCase,
) : ViewModel() {

    private val _state = mutableStateOf<PostListState>(PostListState())
    val state : State<PostListState> = _state

    fun getPosts(token: String) {
        getPostsUseCase(token)
            .distinctUntilChanged()
            .onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = PostListState(posts = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = PostListState(error = result.message ?: "Unexpected error")
                    }
                    is Resource.Loading -> {
                        _state.value = PostListState(isLoading = true)
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}