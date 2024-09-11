package com.demo.marketplacemobileapp.domain.model

import java.sql.Timestamp

data class Cart(
    val id: Long,
    val timestamp: Timestamp,
    val User: User,
    val products: Map<Long, Product>
)
