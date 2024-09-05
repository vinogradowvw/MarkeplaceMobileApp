package com.demo.marketplacemobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import com.demo.marketplacemobileapp.AboutMeComponents.AboutMeHeader
import com.demo.marketplacemobileapp.ItemComponents.ItemPreviewList
import com.demo.marketplacemobileapp.BottomMenuComponents.BottomMenu

class AboutMeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column {
                Column (modifier = Modifier.fillMaxWidth().weight(1f)) {
                    AboutMeHeader("Sample User", "Sample Location")
                    ItemPreviewList()
                }
                BottomMenu(this@AboutMeActivity)
            }
        }
    }
}
