package com.demo.marketplacemobileapp.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.marketplacemobileapp.R
import com.demo.marketplacemobileapp.dataClasses.CartItem


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
fun deliveryOptions() {

    var selectedOption by remember { mutableStateOf(0) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Text(text = "Delivery options", fontSize = 30.sp, modifier = Modifier.padding(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            deliverySelectionElement(
                name = "Standard delivery",
                selected = selectedOption == 0,
                deliveryDuration = "5-8 business days",
                price = "$0.00",
                onClick = { selectedOption = 0 }
            )

            deliverySelectionElement(
                name = "Express delivery",
                selected = selectedOption == 1,
                deliveryDuration = "2-4 business days",
                price = "$5.99",
                onClick = { selectedOption = 1 }
            )
        }
    }
}

@Composable
fun deliverySelectionElement(
    name: String,
    selected: Boolean,
    deliveryDuration: String,
    price: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = name,
            )
            Text(
                text = deliveryDuration,
                color = Color.Gray
            )
        }
        Text(
            text = price,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


@Composable
fun cartItems(productIds: List<Int>) {
    Column {
        productIds.forEach {id -> cartItem(id, R.drawable.image1)}
    }
}

@Composable
fun CartItemElement(cartItem: CartItem) {
    val price = cartItem.price
    Row {
        Text(text = cartItem.name, textAlign=TextAlign.Start)
        Text(text = "$$price",
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f))
    }
}

@Composable
fun cartTotal(cartItems: List<CartItem>) {

    var total = 0f

    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Text(text = "Total", fontSize = 30.sp, modifier = Modifier.padding(20.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            cartItems.forEach { cartItem ->
                total += cartItem.price
                CartItemElement(cartItem)
            }
            CartItemElement(cartItem = CartItem(name = "Total", price = total))
        }
    }
}

@Composable
fun checkoutButton() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {}, Modifier.fillMaxWidth(0.6f).height(70.dp)) {
            Text(text = "Checkout", fontSize = 40.sp)
        }
    }
}