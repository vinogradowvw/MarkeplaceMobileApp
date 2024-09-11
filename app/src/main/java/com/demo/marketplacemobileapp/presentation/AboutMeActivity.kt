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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.demo.marketplacemobileapp.presentation.ui.composable.aboutMe.AboutMeHeader
import com.demo.marketplacemobileapp.presentation.ui.composable.post.PostList
import com.demo.marketplacemobileapp.presentation.ui.composable.bottomMenu.BottomMenu
import com.demo.marketplacemobileapp.presentation.viewModel.post.PostListViewModel

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
                    AboutMeHeader("Sample User", "Sample Location")

                    if (postListState.error.isNotBlank()) {
                        AlertDialog(
                            title = { Text(text = "Error") },
                            text = { Text(text = postListState.error) },
                            onDismissRequest = { /*TODO*/ },
                            confirmButton = { /*TODO*/ })
                    }
                    if (postListState.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                    PostList(postListState.posts)
                }
                BottomMenu(this@AboutMeActivity)
            }
        }
    }
}
