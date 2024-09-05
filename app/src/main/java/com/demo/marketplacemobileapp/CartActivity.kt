package com.demo.marketplacemobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import com.demo.marketplacemobileapp.bottomMenuComponents.BottomMenu
import com.demo.marketplacemobileapp.cart.cartItems
import com.demo.marketplacemobileapp.cart.cartTotal
import com.demo.marketplacemobileapp.cart.checkoutButton
import com.demo.marketplacemobileapp.cart.deliveryOptions
import com.demo.marketplacemobileapp.dataClasses.CartItem

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column {
                Column (modifier = Modifier.fillMaxWidth().weight(1f)) {
                    cartItems(listOf(1, 2, 3))
                    deliveryOptions()

                    val cartItems = listOf(
                        CartItem(name = "Some product product", price = 99.99f),
                        CartItem(name = "Some product product2", price = 99.99f),
                        CartItem(name = "Service fee", price = 1.99f),
                    )
                    cartTotal(cartItems)
                    checkoutButton()
                }
                BottomMenu(activity = this@CartActivity)
            }
        }
    }
}