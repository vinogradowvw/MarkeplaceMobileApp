package com.demo.marketplacemobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import com.demo.marketplacemobileapp.BottomMenuComponents.BottomMenu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column {
                Column (modifier = Modifier.fillMaxWidth().weight(1f)) {
                }
                BottomMenu(this@MainActivity)
            }
        }
    }
}