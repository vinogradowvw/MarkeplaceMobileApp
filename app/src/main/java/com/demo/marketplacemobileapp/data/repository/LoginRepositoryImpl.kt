package com.demo.marketplacemobileapp.data.repository

import com.demo.marketplacemobileapp.data.remote.dto.UserDTO
import com.demo.marketplacemobileapp.data.remote.requests.LoginAPI
import com.demo.marketplacemobileapp.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginAPI: LoginAPI
): LoginRepository {

    override suspend fun login(userDTO: UserDTO) : String {
        return loginAPI.login(userDTO)
    }
}