package com.demo.marketplacemobileapp.presentation.ui.composable.cart

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.demo.marketplacemobileapp.config.config
import com.demo.marketplacemobileapp.data.local.entity.CartItem
import com.demo.marketplacemobileapp.domain.model.DeliveryOptions
import com.demo.marketplacemobileapp.presentation.viewModel.cart.CartViewModel


@Composable
fun cartItem(cartItem: CartItem, cartViewModel: CartViewModel) {

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
        AsyncImage(
            model = "${config.BASE_URL}/image/${cartItem.image}",
            contentDescription = cartItem.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(16.dp))

        )
        Text(text = cartItem.name, modifier = Modifier.padding(2.dp))
        Text(text = cartItem.price.toString(), modifier = Modifier.padding(2.dp))
        IconButton(onClick = {
            cartViewModel.deleteCartItem(cartItem)
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
fun deliveryOptions(cartViewModel: CartViewModel) {
    val selectedOptionState = cartViewModel.selectedDelivery.value
    var selectedOption by remember { mutableStateOf(selectedOptionState.delivery) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Delivery options", fontSize = 30.sp, modifier = Modifier.padding(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            DeliveryOptions.values().forEach { option ->
                deliverySelectionElement(
                    deliveryOption = option,
                    selected = selectedOption == option,
                    onClick = {
                        selectedOption = option
                        cartViewModel.selectDelivery(option)
                    }
                )
            }
        }
    }
}


@Composable
fun deliverySelectionElement(
    deliveryOption: DeliveryOptions,
    selected: Boolean,
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
            Text(text = deliveryOption.name)
            Text(text = deliveryOption.deliveryDuration, color = Color.Gray)
        }

        Text(
            text = "$${deliveryOption.price}",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun cartItems(cartViewModel: CartViewModel) {
    Column {
        cartViewModel.state.value.cartItems.forEach {cartItem -> cartItem(cartItem, cartViewModel) }
    }
}

@Composable
fun CartItemElement(name: String, price: Float) {
    val price = price
    Row {
        Text(text = name, textAlign=TextAlign.Start)
        Text(text = "$$price",
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f))
    }
}

@Composable
fun cartTotal(cartViewModel: CartViewModel, deliveryOption: DeliveryOptions) {

    var total = remember { mutableStateOf(0f) }

    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Text(text = "Total", fontSize = 30.sp, modifier = Modifier.padding(20.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            cartViewModel.state.value.cartItems.forEach { cartItem ->
                total.value += cartItem.price
                CartItemElement(name = cartItem.name, price = cartItem.price)
            }
            CartItemElement(name = deliveryOption.name, price = deliveryOption.price)
            CartItemElement(name = "Total", price = cartViewModel.cartTotal.value)
        }
    }
}

@Composable
fun checkoutButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = {

        },
            Modifier
                .fillMaxWidth(0.6f)
                .height(70.dp)) {
            Text(text = "Checkout", fontSize = 40.sp)
        }
    }
}