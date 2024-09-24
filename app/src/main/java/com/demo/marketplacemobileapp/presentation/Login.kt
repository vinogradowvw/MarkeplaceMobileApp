package com.demo.marketplacemobileapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.demo.marketplacemobileapp.presentation.ui.theme.MarketplaceMobileAppTheme
import com.demo.marketplacemobileapp.presentation.viewModel.login.LoginViewModel
import com.demo.marketplacemobileapp.presentation.viewModel.post.PostListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        }
    }
}