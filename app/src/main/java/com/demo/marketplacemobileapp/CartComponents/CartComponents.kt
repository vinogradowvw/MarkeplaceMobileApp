package com.demo.marketplacemobileapp.CartComponents

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.demo.marketplacemobileapp.CartActivity
import com.demo.marketplacemobileapp.R

@Composable
fun cartInfo() {
    cartItems(listOf(1, 2, 3))
}

@Composable
fun cartItem(productId: Int, drawableResId: Int) {

    val painter = painterResource(id = drawableResId)

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(88.dp)
        .padding(4.dp)
        .border(
            width = 0.5.dp,
            color = Color.Gray,
            shape = RoundedCornerShape(16.dp)
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painter,
            contentDescription = "Some Title",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(16.dp))

        )
        Text(text = "Some Product", modifier = Modifier.padding(2.dp))
        Text(text = "$133.00", modifier = Modifier.padding(2.dp))
        IconButton(onClick = {
        }) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                tint = Color.Black,
                modifier = Modifier
                    .size(30.dp)
                    .padding(2.dp)
            )
        }
    }
}

@Composable
fun deliverySecetion() {
    Column {
        Text(text = "Delivery options")
        Row {
            
        }
    }
}

@Composable
fun cartItems(productIds: List<Int>) {
    Column {
        productIds.forEach {id -> cartItem(id, R.drawable.image1)}
    }
}