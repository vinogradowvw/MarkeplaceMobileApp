package com.demo.marketplacemobileapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.demo.marketplacemobileapp.R
import com.demo.marketplacemobileapp.presentation.ui.composable.common.BottomMenu
import com.demo.marketplacemobileapp.presentation.ui.composable.post.ItemDetailedImages
import com.demo.marketplacemobileapp.presentation.ui.composable.post.ProductInfoDetailed
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.marketplacemobileapp.MarketplaceApplication
import com.demo.marketplacemobileapp.presentation.ui.composable.common.PageHeader
import com.demo.marketplacemobileapp.presentation.ui.composable.post.PostDetailed
import com.demo.marketplacemobileapp.presentation.ui.composable.post.PostList
import com.demo.marketplacemobileapp.presentation.viewModel.cart.CartViewModel
import com.demo.marketplacemobileapp.presentation.viewModel.post.PostListViewModel
import com.demo.marketplacemobileapp.presentation.viewModel.post.PostViewModel

@AndroidEntryPoint
class PostActivity : ComponentActivity() {

    private val postViewModel: PostViewModel by viewModels()

    private val cartViewModel by viewModels<CartViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CartViewModel(
                        (application as MarketplaceApplication).database.cartItemDAO
                    ) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val postId = intent?.getLongExtra("POST_ID", -1L) ?: -1L
        postViewModel.getPostById(postId)
        setContent {
            val postState = postViewModel.state.value
            Column {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(scrollState)
                ) {
                    if (postState.error.isNotBlank()) {
                        var showDialog by remember { mutableStateOf(true) }

                        if (showDialog) {
                            AlertDialog(
                                title = { Text(text = "Error") },
                                text = { Text(text = postState.error) },
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
                    if (postState.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                    postState.post?.let { PostDetailed(it, cartViewModel=cartViewModel) }
                }
                BottomMenu(this@PostActivity)
            }
        }
    }
}