package com.demo.marketplacemobileapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val price: Float,
    val image: Long,
    val name: String
)
