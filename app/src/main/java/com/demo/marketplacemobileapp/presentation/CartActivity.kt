package com.demo.marketplacemobileapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.marketplacemobileapp.MarketplaceApplication
import com.demo.marketplacemobileapp.presentation.ui.composable.common.BottomMenu
import com.demo.marketplacemobileapp.presentation.ui.composable.cart.cartItems
import com.demo.marketplacemobileapp.presentation.ui.composable.cart.cartTotal
import com.demo.marketplacemobileapp.presentation.ui.composable.cart.checkoutButton
import com.demo.marketplacemobileapp.presentation.ui.composable.cart.deliveryOptions
import com.demo.marketplacemobileapp.domain.model.CartItem
import com.demo.marketplacemobileapp.presentation.ui.composable.common.PageHeader
import com.demo.marketplacemobileapp.presentation.viewModel.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : ComponentActivity() {

    private val cartViewModel by viewModels<CartViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CartViewModel(
                        (application as MarketplaceApplication).database.cartItemDAO
                    ) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)) {
                    PageHeader("Cart")
                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                    cartItems(cartViewModel = cartViewModel)
                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                    deliveryOptions(cartViewModel=cartViewModel)

                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                    cartTotal(
                        cartViewModel = cartViewModel,
                        deliveryOption = cartViewModel.selectedDelivery.value.delivery
                    )
                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                    checkoutButton()
                }
                BottomMenu(activity = this@CartActivity)
            }
        }
    }
}