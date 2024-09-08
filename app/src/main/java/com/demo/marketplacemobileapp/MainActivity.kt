package com.demo.marketplacemobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.demo.marketplacemobileapp.bottomMenu.BottomMenu
import com.demo.marketplacemobileapp.postItem.ItemDetailedImages
import com.demo.marketplacemobileapp.postItem.ProductInfoDetailed

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column {
                val description = """
                    Some product Some product Some product Some product Some product Some product Some product Some product Some product Some product""".trimIndent()
                val scrollState = rememberScrollState()
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(scrollState)) {

                    ItemDetailedImages(imageIds = listOf(R.drawable.image2, R.drawable.image1))
                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                    ProductInfoDetailed(name = "Some product",
                        price = 99.99f,
                        description = description,
                        tagNames = listOf("tag1", "tag2", "tag3", "tagag4")
                    )

                }

                BottomMenu(this@MainActivity)
            }
        }
    }
}