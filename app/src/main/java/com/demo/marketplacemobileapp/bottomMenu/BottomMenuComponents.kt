package com.demo.marketplacemobileapp.bottomMenuComponents

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.demo.marketplacemobileapp.AboutMeActivity
import com.demo.marketplacemobileapp.CartActivity

@Composable
fun BottomMenu(activity: ComponentActivity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = {
            val intent = Intent(activity, AboutMeActivity::class.java)
            activity.startActivity(intent)
        }) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Profile",
                tint = Color.Black,
                modifier = Modifier.size(35.dp)
            )
        }

        IconButton(onClick = {

        }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                tint = Color.Black,
                modifier = Modifier.size(35.dp)
            )
        }

        IconButton(onClick = {
            val intent = Intent(activity, CartActivity::class.java)
            activity.startActivity(intent)
        }) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Cart",
                tint = Color.Black,
                modifier = Modifier.size(35.dp)
            )
        }
    }
}