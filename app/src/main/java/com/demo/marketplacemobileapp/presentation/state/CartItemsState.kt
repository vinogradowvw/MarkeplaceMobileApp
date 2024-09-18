package com.demo.marketplacemobileapp.presentation.state

import com.demo.marketplacemobileapp.data.local.entity.CartItem
import com.demo.marketplacemobileapp.domain.model.Post

data class CartItemsState(
    val cartItems: List<CartItem> = emptyList()
)
