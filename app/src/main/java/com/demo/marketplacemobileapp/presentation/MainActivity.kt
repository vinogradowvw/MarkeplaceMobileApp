package com.demo.marketplacemobileapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.demo.marketplacemobileapp.presentation.ui.composable.common.BottomMenu
import com.demo.marketplacemobileapp.presentation.ui.composable.common.PageHeader
import com.demo.marketplacemobileapp.presentation.ui.composable.home.HomeHeader
import com.demo.marketplacemobileapp.presentation.ui.composable.home.HomePageMenu
import com.demo.marketplacemobileapp.presentation.ui.composable.login.LoginFields
import com.demo.marketplacemobileapp.presentation.ui.composable.post.PostDetailed
import com.demo.marketplacemobileapp.presentation.viewModel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val loginState = loginViewModel.token.value
            LoginFields(loginViewModel = loginViewModel)
            if (loginState.error.isNotBlank()) {
                var showDialog by remember { mutableStateOf(true) }
                if (showDialog) {
                    AlertDialog(
                        title = { Text(text = "Login failed.") },
                        text = { Text(text = "Please Try one more time") },
                        onDismissRequest = {
                            showDialog = false
                        },
                        confirmButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text("OK")
                            }
                        }
                    )
                }
            }
            if (loginState.isLoading) {
                AlertDialog(
                    onDismissRequest = {  },
                    title = {
                        Text(text = "Loading")
                    },
                    text = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                            Text(text = "Please wait...")
                        }
                    },
                    confirmButton = {},
                    dismissButton = {}
                )
            }
            if (loginState.token.isNotBlank()) {
                val intent = Intent(this@MainActivity, ProductSearchActivity::class.java)
                this@MainActivity.startActivity(intent)
            }
        //            Column {
//                val scrollState = rememberScrollState()
//                Column (modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f)
//                    .verticalScroll(scrollState)
//                ) {
//                    HomeHeader()
//                    Spacer(modifier = Modifier.height(30.dp))
//                    HomePageMenu(activity = this@MainActivity)
//                }
//                BottomMenu(activity = this@MainActivity)
//            }
        }
    }
}