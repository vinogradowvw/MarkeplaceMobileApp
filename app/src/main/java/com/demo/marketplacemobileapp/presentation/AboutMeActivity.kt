package com.demo.marketplacemobileapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.demo.marketplacemobileapp.presentation.aboutMe.AboutMeHeader
import com.demo.marketplacemobileapp.presentation.posts.ItemPreviewList
import com.demo.marketplacemobileapp.presentation.bottomMenu.BottomMenu
import com.demo.marketplacemobileapp.viewModel.PostViewModel

class AboutMeActivity : ComponentActivity() {

    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        enableEdgeToEdge()
        setContent {
            Column {
                Column (modifier = Modifier.fillMaxWidth().weight(1f)) {
                    AboutMeHeader("Sample User", "Sample Location")
                    ItemPreviewList(viewModel = postViewModel)
                }
                BottomMenu(this@AboutMeActivity)
            }
        }
    }
}
