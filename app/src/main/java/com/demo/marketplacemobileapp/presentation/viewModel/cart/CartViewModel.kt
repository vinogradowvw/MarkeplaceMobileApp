package com.demo.marketplacemobileapp.presentation.viewModel.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.marketplacemobileapp.data.local.dao.CartItemDAO
import com.demo.marketplacemobileapp.data.local.entity.CartItem
import com.demo.marketplacemobileapp.presentation.state.CartItemsState
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class CartViewModel (
    private val cartItemDAO: CartItemDAO
) : ViewModel() {
    private val _state = mutableStateOf(CartItemsState())
    val state: State<CartItemsState> = _state

    init {
        loadCartItems()
    }

    private fun loadCartItems() {
        cartItemDAO.getCartItems().onEach { cartItems ->
            _state.value = CartItemsState(cartItems=cartItems)
        }.launchIn(viewModelScope)
    }

    fun deleteCartItem(id: Long) {
        viewModelScope.launch {
            cartItemDAO.deleteItemById(id)
            loadCartItems()
        }
    }

    suspend fun addItemToCart(cartItem: CartItem) {
        viewModelScope.launch {
            cartItemDAO.addToCart(cartItem)
            loadCartItems()
        }
    }
}