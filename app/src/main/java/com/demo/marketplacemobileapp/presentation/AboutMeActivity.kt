package com.demo.marketplacemobileapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.demo.marketplacemobileapp.presentation.ui.composable.aboutMe.AboutMeHeader
import com.demo.marketplacemobileapp.presentation.ui.composable.post.PostList
import com.demo.marketplacemobileapp.presentation.ui.composable.common.BottomMenu
import com.demo.marketplacemobileapp.presentation.ui.composable.common.PageHeader
import com.demo.marketplacemobileapp.presentation.viewModel.post.PostListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutMeActivity: ComponentActivity() {

    private val postListViewModel: PostListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val postListState = postListViewModel.state.value
            Column {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)) {
                    PageHeader("About Me")
                    AboutMeHeader("Sample User", "Sample Location")

                    if (postListState.error.isNotBlank()) {
                        var showDialog by remember { mutableStateOf(true) }

                        if (showDialog) {
                            AlertDialog(
                                title = { Text(text = "Error") },
                                text = { Text(text = postListState.error) },
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
                    if (postListState.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                    PostList(postListState.posts, activity = this@AboutMeActivity)
                }
                BottomMenu(this@AboutMeActivity)
            }
        }
    }
}
