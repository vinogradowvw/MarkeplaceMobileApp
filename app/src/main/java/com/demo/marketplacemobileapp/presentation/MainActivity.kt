package com.demo.marketplacemobileapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import com.demo.marketplacemobileapp.presentation.ui.composable.common.BottomMenu
import com.demo.marketplacemobileapp.presentation.ui.composable.common.PageHeader
import com.demo.marketplacemobileapp.presentation.ui.composable.home.HomePageMenu
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                ) {
                    PageHeader("Home")
                    HomePageMenu(activity = this@MainActivity)
                }
                BottomMenu(activity = this@MainActivity)
            }
        }
    }
}