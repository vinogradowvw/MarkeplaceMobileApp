package com.demo.marketplacemobileapp.presentation.state

data class LoginState(
    val isLoading: Boolean = false,
    val token: String = "",
    val error: String = ""
)
