package com.demo.marketplacemobileapp

import android.app.Application
import androidx.room.Room
import com.demo.marketplacemobileapp.data.local.database.CartItemDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarketplaceApplication: Application() {
    val database: CartItemDatabase by lazy {
        applicationContext.deleteDatabase("cart-database.db")
        Room.databaseBuilder(
            applicationContext,
            CartItemDatabase::class.java,
            "cart-database.db"
        ).build()
    }
}