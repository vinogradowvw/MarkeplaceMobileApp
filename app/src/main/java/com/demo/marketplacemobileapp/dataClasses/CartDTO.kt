package com.demo.marketplacemobileapp.dataClasses

import java.sql.Timestamp

data class CartDTO(
    val id: Long,
    val timestamp: Timestamp,
    val User: Long,
    val products: Map<Long, Long>
)