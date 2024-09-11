package com.demo.marketplacemobileapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.demo.marketplacemobileapp.presentation.ui.composable.bottomMenu.BottomMenu
import com.demo.marketplacemobileapp.presentation.ui.composable.cart.cartItems
import com.demo.marketplacemobileapp.presentation.ui.composable.cart.cartTotal
import com.demo.marketplacemobileapp.presentation.ui.composable.cart.checkoutButton
import com.demo.marketplacemobileapp.presentation.ui.composable.cart.deliveryOptions
import com.demo.marketplacemobileapp.domain.model.CartItem

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column {
                Column (modifier = Modifier.fillMaxWidth().weight(1f)) {
                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                    cartItems(listOf(1, 2, 3))
                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                    deliveryOptions()

                    val cartItems = listOf(
                        CartItem(name = "Some product product", price = 99.99f),
                        CartItem(name = "Some product product2", price = 99.99f),
                        CartItem(name = "Service fee", price = 1.99f),
                    )
                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                    cartTotal(cartItems)
                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                    checkoutButton()
                }
                BottomMenu(activity = this@CartActivity)
            }
        }
    }
}