package com.demo.marketplacemobileapp.presentation.ui.composable.home

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.marketplacemobileapp.presentation.AboutMeActivity
import com.demo.marketplacemobileapp.presentation.ProductSearchActivity

@Composable
fun HomePageMenu(activity: Activity) {

    Column {
        Button(onClick = {
            val intent = Intent(activity, ProductSearchActivity::class.java)
            activity.startActivity(intent)
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Search", fontSize = 20.sp, color = Color.Black)
        }

        Button(onClick = {
            val intent = Intent(activity, AboutMeActivity::class.java)
            activity.startActivity(intent)
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "About Me",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "About Me", fontSize = 20.sp, color = Color.Black)
        }
    }


}