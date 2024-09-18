package com.demo.marketplacemobileapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.marketplacemobileapp.data.local.dao.CartItemDAO
import com.demo.marketplacemobileapp.data.local.entity.CartItem

@Database(
    entities = [CartItem::class],
    version = 1
)
abstract class CartItemDatabase: RoomDatabase() {
    abstract val cartItemDAO: CartItemDAO
}