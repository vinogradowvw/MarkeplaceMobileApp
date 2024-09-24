package com.demo.marketplacemobileapp.data.remote.requests

import com.demo.marketplacemobileapp.data.remote.dto.UserDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST("/login")
    suspend fun login(@Body userDTO: UserDTO): String

}