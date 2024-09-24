package com.demo.marketplacemobileapp.domain.repository

import com.demo.marketplacemobileapp.data.remote.dto.UserDTO

interface LoginRepository {
    suspend fun login(userDTO: UserDTO): String
}