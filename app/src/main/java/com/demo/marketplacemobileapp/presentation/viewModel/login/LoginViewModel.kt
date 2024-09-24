package com.demo.marketplacemobileapp.presentation.viewModel.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.marketplacemobileapp.common.Resource
import com.demo.marketplacemobileapp.data.remote.dto.UserDTO
import com.demo.marketplacemobileapp.domain.useCase.login.LoginUseCase
import com.demo.marketplacemobileapp.domain.useCase.post.getPost.GetPostByIdUseCase
import com.demo.marketplacemobileapp.presentation.state.LoginState
import com.demo.marketplacemobileapp.presentation.state.PostListState
import com.demo.marketplacemobileapp.presentation.state.PostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _token = mutableStateOf(LoginState())
    val token : State<LoginState> = _token

    fun login(userDTO: UserDTO) {

        loginUseCase(userDTO).onEach { result ->

            when(result) {
                is Resource.Success -> {
                    Log.i("LoginToken", result.data.toString())
                    _token.value = LoginState(token = result.data.toString())
                }
                is Resource.Loading -> {
                    Log.i("LoginToken", "Loading")
                    _token.value = LoginState(isLoading = true)
                }
                is Resource.Error -> {
                    Log.i("LoginToken", result.message?: "Unexpected error")
                    _token.value = LoginState(error = result.message?: "Unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }
}