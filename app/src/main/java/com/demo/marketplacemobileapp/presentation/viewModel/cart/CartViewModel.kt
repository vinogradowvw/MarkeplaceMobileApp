package com.demo.marketplacemobileapp.presentation.viewModel.cart

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.marketplacemobileapp.data.local.dao.CartItemDAO
import com.demo.marketplacemobileapp.data.local.entity.CartItem
import com.demo.marketplacemobileapp.domain.model.DeliveryOptions
import com.demo.marketplacemobileapp.presentation.state.CartItemsState
import com.demo.marketplacemobileapp.presentation.state.DeliveryOptionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CartViewModel (
    private val cartItemDAO: CartItemDAO
) : ViewModel() {

    private val _state = mutableStateOf(CartItemsState())
    val state: State<CartItemsState> = _state

    private val _selectedDelivery = mutableStateOf(DeliveryOptionState())
    val selectedDelivery: State<DeliveryOptionState> = _selectedDelivery

    private val _cartTotal = mutableStateOf(0f)
    val cartTotal: State<Float> = _cartTotal

    init {
        loadCartItems()
    }

    private fun loadCartItems() {
        viewModelScope.launch() {
            cartItemDAO.getCartItems().onEach { cartItems ->
                _state.value = CartItemsState(cartItems = cartItems)
                cartItems.onEach { cartItem -> _cartTotal.value += cartItem.price }
            }.launchIn(this)
        }
    }

    fun deleteCartItem(cartItem: CartItem) {
        viewModelScope.launch() {
            cartItemDAO.deleteItem(cartItem)
            loadCartItems()
            _cartTotal.value -= cartItem.price
        }
    }

    fun addItemToCart(cartItem: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            cartItemDAO.addToCart(cartItem)
            loadCartItems()
        }
    }

    fun selectDelivery(deliveryOption: DeliveryOptions) {
        viewModelScope.launch(Dispatchers.IO) {
            _cartTotal.value -= _selectedDelivery.value.delivery.price
            _selectedDelivery.value = DeliveryOptionState(deliveryOption)
            _cartTotal.value += _selectedDelivery.value.delivery.price
        }
    }
}