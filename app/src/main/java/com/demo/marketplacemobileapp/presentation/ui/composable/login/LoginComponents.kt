package com.demo.marketplacemobileapp.presentation.ui.composable.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.marketplacemobileapp.data.remote.dto.UserDTO
import com.demo.marketplacemobileapp.presentation.viewModel.login.LoginViewModel

@Composable
fun LoginFields(loginViewModel: LoginViewModel) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = username,
                onValueChange = { newText ->
                    username = newText
                },
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
                    .background(Color.LightGray.copy(alpha = 0.1f)),
                textStyle = TextStyle(
                    fontSize = 20.sp
                )
            )
            TextField(
                value = password,
                onValueChange = { newText ->
                    password = newText
                },
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
                    .background(Color.LightGray.copy(alpha = 0.1f)),
                textStyle = TextStyle(
                    fontSize = 20.sp
                )
            )
            Button(onClick = {

                val userDTO = UserDTO(
                    id = 0,
                    username = username.text,
                    email = "testemail@gmail.com",
                    password = password.text,
                    likes = emptyList(),
                    orders = emptyList(),
                    posts = emptyList(),
                )
                Log.i("UserDTO", userDTO.toString())
                loginViewModel.login(userDTO)
            }) {
                Text(text = "Login")
            }
        }
    }
}

@Composable
fun LoginHeader() {}