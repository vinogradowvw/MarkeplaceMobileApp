package com.demo.marketplacemobileapp.domain.useCase.login

import android.util.Log
import com.demo.marketplacemobileapp.common.Resource
import com.demo.marketplacemobileapp.data.remote.dto.UserDTO
import com.demo.marketplacemobileapp.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    operator fun invoke(userDTO: UserDTO): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val token = loginRepository.login(userDTO)
            emit(Resource.Success(token))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage?: "Unexpected HTTP exception"))
        } catch (e: IOException) {
            Log.e("IOException", e.message.toString())
            Log.e("IOException", e.stackTrace.contentDeepToString())
            emit(Resource.Error(message = "Couldn't connect to server"))
        }
    }
}