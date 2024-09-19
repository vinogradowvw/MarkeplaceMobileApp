package com.demo.marketplacemobileapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.demo.marketplacemobileapp.data.local.entity.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDAO {

    @Upsert
    suspend fun addToCart(cartItem: CartItem)

    @Delete
    suspend fun deleteItem(cartItem: CartItem)

    @Query("SELECT * FROM cartitem")
    fun getCartItems(): Flow<List<CartItem>>
}